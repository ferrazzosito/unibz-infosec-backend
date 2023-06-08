package it.unibz.infosec.examproject.review.domain;

import it.unibz.infosec.examproject.user.domain.SafeUserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReviewWithCustomerInfo extends Review {

    private SafeUserEntity customer;

    public ReviewWithCustomerInfo(Review r, SafeUserEntity safeUserEntity) {
        super(r.getTitle(), r.getDescription(), r.getStars(),
                r.getDatePublishing(), r.getProductId(),
                    r.getReplyFromReviewId(), r.getAuthor());
        this.customer = safeUserEntity;
        this.id = r.getId();
    }
}
