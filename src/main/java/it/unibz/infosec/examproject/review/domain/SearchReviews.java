package it.unibz.infosec.examproject.review.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchReviews {

    private final ReviewRepository reviewRepository;

    @Autowired
    public SearchReviews(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review findById(Long id) {
        Optional<Review> searchedReview = reviewRepository.findById(id);

        if(searchedReview.isEmpty()){
            throw new IllegalArgumentException("Review with id " + id + " is not present in the database");
        }
        return searchedReview.get();
    }

    public List<Review> findAll(){
        List<Review> list = reviewRepository.findAll();
        if(list.isEmpty()){
            throw new IllegalArgumentException("No reviews in database");
        }
        return list;
    }

}
