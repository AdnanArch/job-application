package org.adnanarch.jobapplication.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> getAllReviewsOfCompany(@PathVariable Long companyId) {
        List<Review> reviewList = reviewService.findAllReviewsByCompanyId(companyId);
        if (!reviewList.isEmpty()) {
            return new ResponseEntity<>(reviewList, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/reviews", method = RequestMethod.POST)
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isAdded = reviewService.addReview(companyId, review);
        if (isAdded) {
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review Was Not Added", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        boolean isUpdated = reviewService.updateReview(companyId, reviewId, review);
        if (isUpdated){
            return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review was not updated.", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if (isDeleted) {
            return new ResponseEntity<>("Review deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review was not deleted.", HttpStatus.NOT_FOUND);
    }

}
