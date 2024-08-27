package com.example.onlinelearning.Service;

import com.example.onlinelearning.Entity.Course;
import com.example.onlinelearning.Entity.User;
import com.example.onlinelearning.POJO.PojoCourse;
import com.example.onlinelearning.POJO.PojoUser;
import com.example.onlinelearning.Repository.CourseRepository;
import com.example.onlinelearning.Repository.LessonRepository;
import com.example.onlinelearning.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private OrderRepository orderRepository;
    public List<PojoCourse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<PojoCourse> pojoCourses = new ArrayList<>();
        for (Course course : courses) {
            pojoCourses.add(PojoCourse.fromEntity(course));
        }
        return pojoCourses;
    }

    public PojoCourse getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return PojoCourse.fromEntity(course);
    }

    public PojoCourse createCourse(PojoCourse pojoCourse) {
        Course course = PojoCourse.toEntity(pojoCourse);
        Course savedCourse = courseRepository.save(course);
        return PojoCourse.fromEntity(savedCourse);
    }

    public PojoCourse updateCourse(PojoCourse pojoCourse) {
        Course course = courseRepository.findById(pojoCourse.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setCourseName(pojoCourse.getCourseName());
        course.setDescription(pojoCourse.getDescription());

        Course updatedCourse = courseRepository.save(course);
        return PojoCourse.fromEntity(updatedCourse);
    }
    public PojoCourse updateCourse(long courseId, PojoCourse pojoCourse) {
        Course course = PojoCourse.toEntity(pojoCourse);
        Optional<Course> oldcourse = courseRepository.findById(courseId);
        if(oldcourse.isPresent()){
            oldcourse.get().setCourseName(course.getCourseName());
            oldcourse.get().setDescription(course.getDescription());

            Course updatedCourse = courseRepository.save(oldcourse.get());
            return PojoCourse.fromEntity(updatedCourse);
        }
        return null;
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
        lessonRepository.deleteAllByCourseCourseId(courseId);
        orderRepository.deleteAllByCourseCourseId(courseId);
    }

    public List<PojoCourse> findCourseByNameOrDescription(String keyword) {
        List<Course> courses = courseRepository.findByCourseNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
        List<PojoCourse> pojoCourses = new ArrayList<>();
        for (Course course : courses) {
            pojoCourses.add(PojoCourse.fromEntity(course));
        }
        return pojoCourses;
    }
}