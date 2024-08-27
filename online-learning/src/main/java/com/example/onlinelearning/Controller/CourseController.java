package com.example.onlinelearning.Controller;

import com.example.onlinelearning.POJO.PojoCourse;
import com.example.onlinelearning.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<PojoCourse>> getAllCourses() {
        List<PojoCourse> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<PojoCourse> getCourseById(@PathVariable Long courseId) {
        PojoCourse course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }

    @PostMapping("/new")
    public ResponseEntity<PojoCourse> createCourse(@RequestBody PojoCourse pojoCourse) {
        PojoCourse createdCourse = courseService.createCourse(pojoCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<PojoCourse> updateCourse(@PathVariable("courseId") long id, @RequestBody PojoCourse pojoCourse) {
        PojoCourse updatedCourse = courseService.updateCourse(id, pojoCourse);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<PojoCourse>> findCoursesByKeyword(@RequestParam String keyword) {
        List<PojoCourse> courses = courseService.findCourseByNameOrDescription(keyword);
        return ResponseEntity.ok(courses);
    }
}