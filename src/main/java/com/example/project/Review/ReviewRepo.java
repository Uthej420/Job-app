package com.example.project.Review;

import java.util.List;

public interface ReviewRepo {

    List<Review> getReviews(int companyId);

    Review getReviewById(int companyId,int reviewId);
    Review addReview(int companyId, Review review);
    Review updateReview(int companyId, int reviewId, Review review);
    void deleteReview(int companyId,int reviewId);
}
