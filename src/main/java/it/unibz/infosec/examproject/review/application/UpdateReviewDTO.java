package it.unibz.infosec.examproject.review.application;

import java.sql.Date;

public class UpdateReviewDTO extends CreateReviewDTO{

    private Long id;
    public UpdateReviewDTO() {super();}

    public UpdateReviewDTO(Long id, String title, String description, Date datePublishing, Long productId, Long replyFromReviewId){

        super(title, description, datePublishing, productId, replyFromReviewId);
        this.id=id;
    }

    public Long getId(){return id;}

    public void setId(Long id){this.id=id;}
}
