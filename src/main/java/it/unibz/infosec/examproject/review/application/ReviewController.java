package it.unibz.infosec.examproject.review.application;

import it.unibz.infosec.examproject.review.domain.ManageReviews;
import it.unibz.infosec.examproject.review.domain.Review;
import it.unibz.infosec.examproject.review.domain.SearchReviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/reviews")
public class ReviewController {

    private final ManageReviews manageReviews;
    private final SearchReviews searchReviews;

    @Autowired
    public ReviewController (ManageReviews manageReviews, SearchReviews searchReviews) {
        this.manageReviews = manageReviews;
        this.searchReviews = searchReviews;
    }

    @GetMapping("/{id}")
    public Review findById(@PathVariable("id") Long id) {
        return manageReviews.readReview(id);
    }
/*
   String title, String description, Date datePublishing, Long productId, Long replyFromReviewId
 */
    @PostMapping("/create")
    public Review createNewReview(@RequestBody CreateReviewDTO dto) {
        return manageReviews.createReview(dto.getTitle(), dto.getDescription(), dto.getStars(), dto.getDatePublishing(), dto.getProductId(), dto.getReplyFromReviewId(), dto.getAuthor());
    }

    @PostMapping("/update/{id}")
    public Review updateReview(@PathVariable("id") Long id,@RequestBody UpdateReviewDTO dto){
        return manageReviews.updateReview(id, dto.getTitle(), dto.getDescription());
    }

    @DeleteMapping("/delete/{id}")
    public Review deleteReview(@PathVariable("id") Long id)  {
        return manageReviews.deleteReview(id);
    }

    @GetMapping("/getAll")
    public List<Review> findAll(){
        return searchReviews.findAll();
    }
}