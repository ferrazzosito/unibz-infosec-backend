package it.unibz.infosec.examproject.product.domain;

import it.unibz.infosec.examproject.review.domain.Review;
import it.unibz.infosec.examproject.user.domain.SafeUserEntity;

public class ReviewWithCustomerInfo extends Review {

    private final SafeUserEntity customer;

    public ReviewWithCustomerInfo(Review r, SafeUserEntity safeUserEntity) {
        super(r.getTitle(), r.getDescription(), r.getStars(),
                r.getDatePublishing(), r.getProductId(),
                    r.getReplyFromReviewId(), r.getAuthor());
        this.customer = safeUserEntity;
    }
}
