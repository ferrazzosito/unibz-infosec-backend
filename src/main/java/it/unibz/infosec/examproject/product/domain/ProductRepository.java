package it.unibz.infosec.examproject.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @NonNull
    @Override
    Optional<Product> findById(@NonNull Long id);

    @NonNull
    List<Product> findByVendorId(@NonNull Long vendorId);
}
