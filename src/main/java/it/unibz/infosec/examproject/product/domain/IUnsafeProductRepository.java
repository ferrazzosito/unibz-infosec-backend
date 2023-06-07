package it.unibz.infosec.examproject.product.domain;

import org.springframework.lang.NonNull;

import java.util.List;

public interface IUnsafeProductRepository {
    @NonNull
    List<Product> findByName(@NonNull String name);

    @NonNull
    List<Product> findByNameAndVendorId(@NonNull String name, @NonNull Long vendorId);
}
