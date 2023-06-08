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
    Long id;

    private String title;

    private String description;

    private int stars;

    private Date datePublishing;

    private Long productId;

    private Long replyFromReviewId;

    private Long author;

    public Review() {}

    public Review(String title, String description, int stars, Date datePublishing, Long productId, Long replyFromReviewId, Long author) {
        this.title = title;
        this.description = description;
        this.stars = stars;
        this.datePublishing = datePublishing;
        this.productId = productId;
        this.replyFromReviewId = replyFromReviewId;
        this.author = author;
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

    public int getStars() { return stars; }

    public Date getDatePublishing() {
        return datePublishing;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getReplyFromReviewId() {
        return replyFromReviewId;
    }

    public Long getAuthor() {
        return author;
    }
}
