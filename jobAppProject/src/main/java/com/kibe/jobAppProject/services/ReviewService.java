package com.kibe.jobAppProject.services;

import com.kibe.jobAppProject.entity.Company;
import com.kibe.jobAppProject.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    List<Review> getAllCompanyReviews(Long id);
    Review getReviewById(Long companyId, Long reviewId);
    boolean createReview(Long companyId, Review review);
    boolean updateReview(Long companyId, Long reviewId, Review updatedReview);
    boolean deleteReviewById(Long companyId,Long reviewId);
}
