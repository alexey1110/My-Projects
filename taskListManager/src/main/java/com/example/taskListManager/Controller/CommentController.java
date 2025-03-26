package com.example.taskListManager.Controller;

import com.example.taskListManager.DTO.CommentDTO;
import com.example.taskListManager.DTO.UserDTO;
import com.example.taskListManager.Service.CommentService;
import com.example.taskListManager.Service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks/{taskId}/comments")
@Validated
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    private UserDTO getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userService.getUserByEmail(email);
    }

    //???Добавление комментария (Админ и пользователь)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public CommentDTO createComment(@PathVariable Long taskId,
                                    @RequestParam @NotNull(message = "Текст комментария не может быть пустым") String text){
        UserDTO user = getCurrentUser();
        return commentService.addComment(taskId, user.getUserId(), text);
    }

    //???Получение всех комментариев по задаче(Админ и пользователь)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<CommentDTO> getAllCommentsByTask(@PathVariable Long taskId){
        UserDTO user = getCurrentUser();
        return commentService.getAllCommentByTask(taskId, user.getUserId());
    }

    //111Удалене коментария (Админ)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void  deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }
}
