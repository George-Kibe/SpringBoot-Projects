package com.kibe.jobAppProject.controllers;

import com.kibe.jobAppProject.entity.Review;
import com.kibe.jobAppProject.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/{companyId}")
    public ResponseEntity<List<Review>> getCompanyReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllCompanyReviews(companyId), HttpStatus.OK);
    }
    @PostMapping("/{companyId}")
    public ResponseEntity<String> createCompanyReview(@PathVariable Long companyId,
                                                        @RequestBody Review review){
        boolean created = reviewService.createReview(companyId, review);
        if (created){
            return  new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Some Error occurred!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @GetMapping("/{companyId}/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review =  reviewService.getReviewById(companyId, reviewId);
        if (review !=null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{companyId}/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean updated =  reviewService.updateReview(companyId, reviewId, review);
        if (updated){
            return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Found / Not Updated", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{companyId}/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId){
        boolean deleted =  reviewService.deleteReviewById(companyId, reviewId);
        if (deleted){
            return new ResponseEntity<>("Review deleted successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Found / Not deleted", HttpStatus.NOT_FOUND);
    }
}
