package com.example.project.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviews(@PathVariable("companyId") int companyId){
        return ResponseEntity.ok(reviewService.getReviews(companyId));
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable("companyId") int companyId,@PathVariable("reviewId") int reviewId){
        return ResponseEntity.ok(reviewService.getReviewById(companyId,reviewId));
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@PathVariable("companyId") int companyId, @RequestBody Review review){
        return new ResponseEntity(reviewService.addReview(companyId,review),HttpStatus.CREATED);
    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable("companyId")int companyId,@PathVariable("reviewId")int reviewId,@RequestBody Review review){
        return new ResponseEntity<>(reviewService.updateReview(companyId,reviewId,review),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/reviews/{reviewId}")
    public void deleteReview(@PathVariable("companyId") int companyId,@PathVariable("reviewId") int reviewId){
        reviewService.deleteReview(companyId, reviewId);
    }

}
