package it.unibz.infosec.examproject.review.domain;

import it.unibz.infosec.examproject.product.domain.ManageProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class ManageReviews {

    private final ReviewRepository reviewRepository;
    private SearchReviews searchReviews;
    private final ManageProducts manageProducts;

    @Autowired
    public ManageReviews(ReviewRepository reviewRepository, ManageProducts manageProducts) {
        this.reviewRepository = reviewRepository;
        this.manageProducts = manageProducts;
    }

    private Review validateReview(Long id) {
        final Optional<Review> maybeReview = reviewRepository.findById(id);
        if (maybeReview.isEmpty() ) {
            throw new IllegalArgumentException("Review with id '" + id + "' does not exist yet!");
        }
        return maybeReview.get();
    }

    //TODO: validate author Id
    public Review createReview(String title, String description, int stars, Date datePublishing, Long productId, Long replyFromReviewId, Long author) {

        Long replyReviewId = replyFromReviewId == 0 ? 0 : validateReview(replyFromReviewId).getId();

        return reviewRepository.save(new Review(
                title,
                description,
                stars,
                datePublishing,
                manageProducts.readProduct(productId).getId(),
                replyReviewId,
                author)
        );
    }

    public Review readReview(Long id) {
        return validateReview(id);
    }

    public Review updateReview (Long id, String title, String description) {
        final Review review = validateReview(id);
        review.setTitle(title);
        review.setDescription(description);
        return reviewRepository.save(review);
    }

    public Review deleteReview (Long id) {
        final Review review = validateReview(id);
        reviewRepository.delete(review);
        return review;
    }

}
