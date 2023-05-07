package it.unibz.infosec.examproject.order.domain;

import it.unibz.infosec.examproject.product.domain.Product;
import it.unibz.infosec.examproject.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(Long id);

}
