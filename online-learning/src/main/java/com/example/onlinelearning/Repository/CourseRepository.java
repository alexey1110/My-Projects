package com.example.onlinelearning.Repository;

import com.example.onlinelearning.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String courseName, String description);// Дополнительные методы для работы с курсами могут быть добавлены здесь
    //List<Course> findByName(String name);
}
