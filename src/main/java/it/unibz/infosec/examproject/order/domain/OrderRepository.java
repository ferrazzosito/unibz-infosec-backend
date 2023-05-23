package it.unibz.infosec.examproject.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @NonNull
    @Override
    Optional<Order> findById(@NonNull Long id);
}
