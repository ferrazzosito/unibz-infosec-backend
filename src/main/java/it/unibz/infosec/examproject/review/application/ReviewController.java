package it.unibz.infosec.examproject.review.application;

import it.unibz.infosec.examproject.review.domain.ManageReviews;
import it.unibz.infosec.examproject.review.domain.Review;
import it.unibz.infosec.examproject.review.domain.SearchReviews;
import it.unibz.infosec.examproject.user.domain.UserRepository;
import it.unibz.infosec.examproject.util.RESTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/reviews")
public class ReviewController {

    private final UserRepository userRepository;
    private final ManageReviews manageReviews;
    private final SearchReviews searchReviews;

    @Autowired
    public ReviewController(ManageReviews manageReviews, SearchReviews searchReviews, UserRepository userRepository) {
        this.manageReviews = manageReviews;
        this.searchReviews = searchReviews;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public Review findById(@PathVariable("id") Long id) {
        return manageReviews.readReview(id);
    }

    @PostMapping("/create")
    public Review createNewReview(@RequestBody CreateReviewDTO dto) {
        return manageReviews.createReview(dto.getTitle(),
                dto.getDescription(), dto.getStars(),
                    dto.getDatePublishing(), dto.getProductId(),
                        dto.getReplyFromReviewId(), RESTUtils.getLoggedUser(userRepository).getId());
    }

    @PostMapping("/update/{id}")
    public Review updateReview(@PathVariable("id") Long id, @RequestBody UpdateReviewDTO dto) {
        return manageReviews.updateReview(id,
                RESTUtils.getLoggedUser(userRepository).getId(), dto.getTitle(), dto.getDescription());
    }

    @DeleteMapping("/delete/{id}")
    public Review deleteReview(@PathVariable("id") Long id)  {
        return manageReviews.deleteReview(id, RESTUtils.getLoggedUser(userRepository).getId());
    }

    @GetMapping("/getAll")
    public List<Review> findAll(){
        return searchReviews.findAll();
    }

    @GetMapping("/mine")
    public List<Review> getByCustomer() {
        return manageReviews.getByCustomer(RESTUtils.getLoggedUser(userRepository).getId());
    }

    @GetMapping("/{id}/replies")
    public List<Review> getReplies(@PathVariable("id") Long id) {
        return manageReviews.getReplies(id);
    }
}