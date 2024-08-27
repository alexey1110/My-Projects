package com.example.onlinelearning.Service;

import com.example.onlinelearning.Entity.*;
import com.example.onlinelearning.POJO.PojoLesson;
import com.example.onlinelearning.POJO.PojoOrder;
import com.example.onlinelearning.POJO.PojoReview;
import com.example.onlinelearning.Repository.CourseRepository;
import com.example.onlinelearning.Repository.ReviewRepository;
import com.example.onlinelearning.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    public List<PojoReview> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        List<PojoReview> pojoReviews = new ArrayList<>();
        for (Review review : reviews) {
            pojoReviews.add(PojoReview.fromEntity(review));
        }
        return pojoReviews;
    }

    public PojoReview getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return PojoReview.fromEntity(review);
    }

    public PojoReview createReview(long courseId, long userId, PojoReview pojoReview) {
        System.out.println("Creating review. Rating: " + pojoReview.getRating()); // Логирование значения рейтинга
        Review review = PojoReview.toEntity(pojoReview);

        // Установка courseId и userId для отзыва

        Course course = courseRepository.findById(courseId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        review.setCourse(course);
        review.setUser(user);

        return PojoReview.fromEntity(reviewRepository.save(review));
    }

    public PojoReview updateReview(long reviewId, PojoReview pojoReview) {
        Review review = PojoReview.toEntity(pojoReview);
        Optional<Review> oldreview = reviewRepository.findById(reviewId);
        if(oldreview.isPresent()) {
            oldreview.get().setComment(review.getComment());
            oldreview.get().setRating(review.getRating());

            Review updatedReview = reviewRepository.save(oldreview.get());
            return PojoReview.fromEntity(updatedReview);
        }
        return null;
    }
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public List<PojoReview> findReviewsByRating(int rating) {
        List<Review> reviews = reviewRepository.findByRating(rating);
        List<PojoReview> pojoReviews = new ArrayList<>();
        for (Review review : reviews) {
            pojoReviews.add(PojoReview.fromEntity(review));
        }
        return pojoReviews;
    }
    public List<PojoReview> findReviewsByCourseId(long courseId, long userId) {

        List<Review> reviews = reviewRepository.findByCourseCourseIdAndUserUserId(courseId, userId);
        List<PojoReview> pojoReviews = new ArrayList<>();
        for (Review review : reviews) {
            pojoReviews.add(PojoReview.fromEntity(review));
        }
        return pojoReviews;
    }
}
