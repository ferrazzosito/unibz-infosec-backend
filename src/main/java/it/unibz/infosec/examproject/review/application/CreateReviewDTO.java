package it.unibz.infosec.examproject.review.application;

import java.sql.Date;

public class CreateReviewDTO {

    private String title;
    private String description;
    private Date datePublishing;
    private Long productId;
    private Long replyFromReviewId;

    public CreateReviewDTO() {}

    public CreateReviewDTO(String title, String description, Date datePublishing, Long productId, Long replyFromReviewId) {
        this.title = title;
        this.description = description;
        this.datePublishing = datePublishing;
        this.productId = productId;
        this.replyFromReviewId = replyFromReviewId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatePublishing() {
        return datePublishing;
    }

    public void setDatePublishing(Date datePublishing) {
        this.datePublishing = datePublishing;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getReplyFromReviewId() {
        return replyFromReviewId;
    }

    public void setReplyFromReviewId(Long replyFromReviewId) {
        this.replyFromReviewId = replyFromReviewId;
    }
}
