package it.unibz.infosec.examproject.review.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Date datePublishing;

    private Long productId;

    private Long replyFromReviewId;

    public Review() {}

    public Review(String title, String description, Date datePublishing, Long productId, Long replyFromReviewId) {
        this.title = title;
        this.description = description;
        this.datePublishing = datePublishing;
        this.productId = productId;
        this.replyFromReviewId = replyFromReviewId;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDatePublishing() {
        return datePublishing;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getReplyFromReviewId() {
        return replyFromReviewId;
    }
}
