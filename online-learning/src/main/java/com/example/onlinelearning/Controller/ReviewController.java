package com.example.onlinelearning.Controller;

import com.example.onlinelearning.POJO.PojoReview;
import com.example.onlinelearning.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users/{userId}/courses/{courseId}/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<PojoReview>> getAllReviews() {
        List<PojoReview> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<PojoReview> getReviewById(@PathVariable Long reviewId) {
        PojoReview review = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(review);
    }
    @PostMapping
    public ResponseEntity<PojoReview> createReview(@PathVariable("courseId") long courseId, @PathVariable("userId") long userId, @RequestBody PojoReview pojoReview) {
        System.out.println("Received request to create review. Rating: " + pojoReview.getRating()); // Логирование значения рейтинга
        PojoReview createdReview = reviewService.createReview(courseId, userId, pojoReview);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<PojoReview> updateReview(@PathVariable("reviewId") long id, @RequestBody PojoReview pojoReview) {
        PojoReview updatedReview = reviewService.updateReview(id, pojoReview);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<PojoReview>> findReviewsByRating(@RequestParam int rating) {
//        List<PojoReview> reviews = reviewService.findReviewsByRating(rating);
//        return ResponseEntity.ok(reviews);
//    }
    @GetMapping("/search")
    public ResponseEntity<List<PojoReview>> findReviewsByCourseId(@PathVariable("courseId") long courseId, @PathVariable("userId") long userId) {
        List<PojoReview> reviews = reviewService.findReviewsByCourseId(courseId, userId);
        return ResponseEntity.ok(reviews);
    }

}