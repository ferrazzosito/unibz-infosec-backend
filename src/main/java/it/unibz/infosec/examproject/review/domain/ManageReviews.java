package it.unibz.infosec.examproject.review.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class ManageReviews {

    private ReviewRepository reviewRepository;
    private SearchReviews searchReviews;

    @Autowired
    public ManageReviews(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    private Review validateReview (Long id) {

        Optional<Review> maybeReview = reviewRepository.findById(id);

        if(maybeReview.isEmpty())
            throw new IllegalArgumentException("Review with id '" + id + "' does not exist yet!");

        return maybeReview.get();
    }

    public Review createReview(String title, String description, Date datePublishing, Long productId, Long replyFromReviewId) {

        //TODO: search how to put annotation that replyFromReviewId is optional
        //TODO: do the check that if present replyFromReviewId has to exist
        //TODO: do the check that if present productId has to exist

        return reviewRepository.save(new Review(title, description, datePublishing, productId, replyFromReviewId));
    }

    public Review readReview(Long id) {

        Review review = validateReview(id);

        return review;

    }

    public Review updateReview (Long id, String title, String description) {

        Review review = validateReview(id);

        review.setTitle(title);
        review.setDescription(description);

        return reviewRepository.save(review);
    }

    public Review deleteReview (Long id) {

        Review review = validateReview(id);

        reviewRepository.delete(review);

        return review;
    }

}
