package com.example.project.Review;


import com.example.project.Company.Company;
import com.example.project.Company.CompanyJpaRepo;
import com.example.project.Company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewService implements ReviewRepo{
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyJpaRepo companyJpaRepo;
    @Autowired
    private ReviewJpaRepo reviewJpaRepo;


    @Override
    public List<Review> getReviews(int companyId){
        List<Review> reviews = reviewJpaRepo.findByCompanyId(companyId);
        //System.out.println(reviews);
        return  reviews;
    }

    @Override
    public Review addReview(int companyId, Review review) {
        Company company = companyJpaRepo.findById(companyId).get();
        review.setCompany(company);
        return reviewJpaRepo.save(review);
    }
    @Override
    public Review getReviewById(int companyId,int reviewId){
        List<Review> reviews = reviewJpaRepo.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getReviewId() == (reviewId))
                .findFirst()
                .orElse(null);
    }
    @Override
    public Review updateReview(int companyId, int reviewId, Review review) {
        if(companyService.getCompanyById(companyId) != null){
            review.setCompany(companyService.getCompanyById(companyId));
            review.setReviewId(reviewId);
        }
        return reviewJpaRepo.save(review);

    }

    @Override
    public void deleteReview(int companyId,int reviewId) {

            if (companyService.getCompanyById(companyId) != null) {
                Company company = companyService.getCompanyById(companyId);

                Review review = reviewJpaRepo.findById(reviewId).get();
                company.getReview().remove(review);
                review.setCompany(null);
                companyService.updateCompany(companyId,company);
                reviewJpaRepo.deleteById(reviewId);
            }
            throw new ResponseStatusException(HttpStatus.MOVED_PERMANENTLY);


    }


}
