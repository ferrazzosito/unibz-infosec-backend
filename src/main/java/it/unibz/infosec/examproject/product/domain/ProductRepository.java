package it.unibz.infosec.examproject.product.domain;

import it.unibz.infosec.examproject.review.domain.Review;
import it.unibz.infosec.examproject.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

}
