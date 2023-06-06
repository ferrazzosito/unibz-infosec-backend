package it.unibz.infosec.examproject.product.domain;

import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface IUnsafeProductRepository {
    @NonNull
    List<Product> findByName(@NonNull String name);
}
