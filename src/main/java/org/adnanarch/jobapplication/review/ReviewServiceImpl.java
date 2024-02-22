package org.adnanarch.jobapplication.review;

import org.adnanarch.jobapplication.company.Company;
import org.adnanarch.jobapplication.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> findAllReviewsByCompanyId(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.searchCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        Review review = getReviewById(companyId, reviewId);
        if (review != null) {
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewID) {
        List<Review> reviewList = findAllReviewsByCompanyId(companyId);
        for (Review review : reviewList) {
            if (review.getId().equals(reviewID)) {
                return review;
            }
        }
        return null;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Review review = getReviewById(companyId, reviewId);
        if (review != null) {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
