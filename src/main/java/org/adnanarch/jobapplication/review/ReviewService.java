package org.adnanarch.jobapplication.review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReviewsByCompanyId(Long companyId);
    boolean addReview(Long companyId, Review review);
    boolean updateReview(Long companyId, Long reviewId, Review updatedReview);
    Review getReviewById(Long companyId, Long reviewId);
    boolean deleteReview(Long companyId, Long reviewId);
}