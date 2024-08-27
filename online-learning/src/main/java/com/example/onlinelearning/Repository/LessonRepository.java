package com.example.onlinelearning.Repository;

import com.example.onlinelearning.Entity.Lesson;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByLessonTitleContainingIgnoreCase(String title);
    public List<Lesson> findByCourseCourseId(long courseId);

    public Lesson findByLessonIdAndCourseCourseId(long id, long courseId);

    public void deleteAllByCourseCourseId(long courseId);

    //List<Lesson> findByCourseCourseId(long courseId);
    @Transactional
    public void deleteByLessonIdAndCourseCourseId(long id, long courseId);
    // Дополнительные методы для работы с уроками могут быть добавлены здесь
}