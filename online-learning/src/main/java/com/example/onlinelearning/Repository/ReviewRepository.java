package com.example.onlinelearning.Repository;

import com.example.onlinelearning.Entity.Course;
import com.example.onlinelearning.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCourseCourseIdAndUserUserId(long courseId, long userId);
    List<Review> findByRating(int rating);
    // Дополнительные методы для работы с отзывами могут быть добавлены здесь
}