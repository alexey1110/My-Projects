package com.example.taskListManager.Controller;

import com.example.taskListManager.DTO.TaskDTO;
import com.example.taskListManager.DTO.TaskDTORequest;
import com.example.taskListManager.DTO.UserDTO;
import com.example.taskListManager.Model.Priority;
import com.example.taskListManager.Model.Status;
import com.example.taskListManager.Service.TaskService;
import com.example.taskListManager.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@Validated
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController (TaskService taskService, UserService userService){
        this.taskService = taskService;
        this.userService = userService;
    }

    private UserDTO getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userService.getUserByEmail(email);
    }

    //111Просмотр задач в которых пользователь назначен исполнителем (Пользователь)
    @GetMapping("/my-tasks")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Page<TaskDTO> getAllExecTasks(@RequestParam(required = false, defaultValue = "PENDING") Status status,
                                         @RequestParam(required = false, defaultValue = "HIGH") Priority priority,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size){
        UserDTO user = getCurrentUser();
        return taskService.getAllTasksByExecutor(user.getUserId(), status, priority, page, size);
    }

    //111Просмотр задачи в которой пользователь назначени исполнителем по id (Пользователь)
    @GetMapping("/my-tasks/{taskId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public TaskDTO getExecTaskById(@PathVariable Long taskId){
        UserDTO user = getCurrentUser();
        return taskService.getAssignedTaskById(user.getUserId(), taskId);
    }

    //111Просмотр всех задач (Админ)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<TaskDTO> getAllTasks(@RequestParam(required = false, defaultValue = "PENDING") Status status,
                                     @RequestParam(required = false, defaultValue = "HIGH") Priority priority,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size){
        return taskService.getAllTask(status, priority, page, size);
    }

    //111Получение задач конкретного автора (Админ)
    @GetMapping("/author/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<TaskDTO> getAllTaskByAuthor(@PathVariable Long userId,
                                            @RequestParam(required = false, defaultValue = "PENDING") Status status,
                                            @RequestParam(required = false, defaultValue = "HIGH") Priority priority,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "5") int size){
        return taskService.getAllTasksByAuthor(userId, status, priority, page, size);
    }

    //111Получение задач конкретного исполнителя (Админ)
    @GetMapping("/executor/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<TaskDTO> getAllTaskByExecutor(@PathVariable Long userId,
                                              @RequestParam(required = false, defaultValue = "PENDING") Status status,
                                              @RequestParam(required = false, defaultValue = "HIGH") Priority priority,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "5") int size){
        return taskService.getAllTasksByExecutor(userId, status, priority, page, size);
    }

    //111Создание задачи (Админ)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TaskDTO createTask(@RequestBody @Valid TaskDTORequest request){
        UserDTO user = getCurrentUser();
        return taskService.createTask(user.getUserId(), request.getTitle(), request.getDescription());
    }

    //111Получение задачи по id (Админ)
    @GetMapping("/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public TaskDTO getTask(@PathVariable Long taskId){
        return taskService.getTaskById(taskId);
    }

    //111Изменение задачи (Админ)
    @PutMapping("/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public TaskDTO updateTask(@PathVariable Long taskId, @RequestBody @Valid TaskDTORequest request){
        return taskService.updateTask(taskId, request.getTitle(), request.getDescription());
    }

    //111Удаление задачи (Админ)
    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
    }

    //111Изменение статуса задачи (Админ и пользователь)
    @PatchMapping("/{taskId}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public TaskDTO updateTaskStatus(@PathVariable Long taskId,
                                    @RequestParam(required = true) @NotNull(message = "Статус задачи не может быть пустым") Status status){
        return taskService.updateStatus(taskId, status);
    }

    //111Изменение приоритета задачи (Админ)
    @PatchMapping("/{taskId}/priority")
    @PreAuthorize("hasRole('ADMIN')")
    public TaskDTO updateTaskPriority(@PathVariable Long taskId,
                                      @RequestParam(required = true) @NotNull(message = "Приоритет задачи не может быть пустым") Priority priority){
        return taskService.updatePriority(taskId, priority);
    }

    //111Добавление исполнителя задачи (Админ)
    @PatchMapping("/{taskId}/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public TaskDTO assignExecutor(@PathVariable Long taskId,
                                  @RequestParam(required = true) @NotNull(message = "ID исполнителя не может быть пустым") Long executorId){
        return taskService.assignExecutor(taskId, executorId);
    }
}
