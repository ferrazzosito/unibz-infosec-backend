package it.unibz.infosec.examproject.review.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @NonNull
    @Override
    Optional<Review> findById(@NonNull Long id);
}
