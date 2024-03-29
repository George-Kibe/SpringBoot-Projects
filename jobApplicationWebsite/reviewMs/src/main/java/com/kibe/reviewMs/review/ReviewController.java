package com.kibe.reviewMs.review;
import com.kibe.reviewMs.messaging.ReviewMessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;
    private ReviewMessageProducer reviewMessageProducer;

    public ReviewController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getCompanyReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllCompanyReviews(companyId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> createCompanyReview(@RequestParam Long companyId,
                                                      @RequestBody Review review){
        boolean created = reviewService.createReview(companyId,review);
        if (created){
            reviewMessageProducer.sendMessage(review);
            return  new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Some Error occurred!", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
        Review review =  reviewService.getReviewById(reviewId);
        if (review !=null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean updated =  reviewService.updateReview(reviewId, review);
        if (updated){
            return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Found / Not Updated", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean deleted =  reviewService.deleteReviewById(reviewId);
        if (deleted){
            return new ResponseEntity<>("Review deleted successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Found / Not deleted", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/averageRating")
    public Double getAverageRating(@RequestParam Long companyId){
        List<Review> reviewList = reviewService.getAllCompanyReviews(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average()
                .orElse(0.0);

    }
}

