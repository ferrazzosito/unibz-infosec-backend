package it.unibz.infosec.examproject.review.domain;

import it.unibz.infosec.examproject.product.domain.ManageProducts;
import it.unibz.infosec.examproject.user.domain.ManageUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ManageReviews {

    private final ReviewRepository reviewRepository;
    private SearchReviews searchReviews;
    private final ManageProducts manageProducts;
    private final ManageUsers manageUsers;

    @Autowired
    public ManageReviews(ReviewRepository reviewRepository, ManageProducts manageProducts, ManageUsers manageUsers) {
        this.reviewRepository = reviewRepository;
        this.manageProducts = manageProducts;
        this.manageUsers = manageUsers;
    }

    private Review validateReview(Long id) {
        final Optional<Review> maybeReview = reviewRepository.findById(id);
        if (maybeReview.isEmpty() ) {
            throw new IllegalArgumentException("Review with id '" + id + "' does not exist yet!");
        }
        return maybeReview.get();
    }

    public Review createReview(String title, String description, int stars, Date datePublishing, Long productId, Long replyFromReviewId, Long author) {
        final Long replyReviewId = replyFromReviewId == null || replyFromReviewId == 0 ? 0 : validateReview(replyFromReviewId).getId();
        return reviewRepository.save(new Review(
                title,
                description,
                stars,
                datePublishing,
                manageProducts.readProduct(productId).getId(),
                replyReviewId,
                manageUsers.readUser(author).getId()
        ));
    }

    public Review readReview(Long id) {
        return validateReview(id);
    }

    public Review updateReview(Long id, Long author, String title, String description) {
        final Review review = validateReview(id);
        if (!review.getAuthor().equals(author)) {
            throw new IllegalArgumentException("Only the author of a review can update it");
        }
        review.setTitle(title);
        review.setDescription(description);
        return reviewRepository.save(review);
    }

    public Review deleteReview(Long id, Long author) {
        final Review review = validateReview(id);
        if (!review.getAuthor().equals(author)) {
            throw new IllegalArgumentException("Only the author of a review can delete it");
        }
        reviewRepository.delete(review);
        return review;
    }

    public List<Review> getByCustomer(Long customerId) {
        return reviewRepository.findByAuthor(customerId);
    }

    public List<Review> getByProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public List<Review> getReplies(Long reviewId) {
        return reviewRepository.findByReplyFromReviewId(reviewId);
    }
}
