    package com.example.onlinelearning.POJO;

    import com.example.onlinelearning.Entity.Review;
    import lombok.Getter;
    import lombok.Setter;

    @Getter
    @Setter
    public class PojoReview {
        private Long reviewId;
        private int rating;
        private String comment;
        private Long courseId;
        private Long userId;
        public static PojoReview fromEntity(Review review) {
            var pojo = new PojoReview();
            pojo.setReviewId(review.getReviewId());
            pojo.setRating(review.getRating());
            pojo.setComment(review.getComment());
            pojo.setCourseId(review.getCourseId());
            pojo.setUserId(review.getUserId());
            return pojo;
        }

        public static Review toEntity(PojoReview pojoReview) {
            var review = new Review();
            review.setReviewId(pojoReview.getReviewId());
            review.setRating(pojoReview.getRating());
            review.setComment(pojoReview.getComment());
            return review;
        }
    }
