package com.kibe.reviewMs.review;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    List<Review> getAllCompanyReviews(Long id);
    Review getReviewById(Long reviewId);
    boolean createReview(Long companyId, Review review);
    boolean updateReview(Long reviewId, Review updatedReview);
    boolean deleteReviewById(Long reviewId);
}
