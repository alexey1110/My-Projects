package com.example.onlinelearning.Controller;

import com.example.onlinelearning.POJO.PojoLesson;
import com.example.onlinelearning.Service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("courses/{courseId}/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<PojoLesson>> getAllLessons(@PathVariable("courseId") int courseId) {
        List<PojoLesson> lessons = lessonService.getAllLessons(courseId);
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<PojoLesson> getLessonById(@PathVariable("courseId") int courseId, @PathVariable Long lessonId) {
        PojoLesson lesson = lessonService.getLessonById(courseId, lessonId);
        return ResponseEntity.ok(lesson);
    }

    @PostMapping
    public ResponseEntity<PojoLesson> createLesson(@PathVariable("courseId") long courseId, @RequestBody PojoLesson pojoLesson) {
        PojoLesson createdLesson = lessonService.createLesson(courseId, pojoLesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLesson);
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<PojoLesson> updateLesson(@PathVariable("courseId") long courseId, @PathVariable("lessonId") long lessonId, @RequestBody PojoLesson pojoLesson) {
        PojoLesson updatedLesson = lessonService.updateLesson(courseId, lessonId, pojoLesson);
        return ResponseEntity.ok(updatedLesson);
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> deleteLesson(@PathVariable("courseId") long courseId, @PathVariable long lessonId) {
        lessonService.deleteLesson(courseId, lessonId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<PojoLesson>> findLessonsByTitlу(@PathVariable("courseId") long courseId, @RequestParam String title) {
//        List<PojoLesson> lessons = lessonService.findLessonByTitle(courseId, title);
//        return ResponseEntity.ok(lessons);
//    }
@GetMapping("/search")
public ResponseEntity<List<PojoLesson>> findLessonsByCourseId(@PathVariable("courseId") long courseId) {
    List<PojoLesson> lessons = lessonService.findLessonByCourseId(courseId);
    return ResponseEntity.ok(lessons);
}
}
