package it.unibz.infosec.examproject.product.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchProducts {

    private final ProductRepository productRepository;
    private final UnsafeProductRepository unsafeProductRepository;

    @Autowired
    public SearchProducts(ProductRepository productRepository, UnsafeProductRepository unsafeProductRepository) {
        this.productRepository = productRepository;
        this.unsafeProductRepository = unsafeProductRepository;
    }

    public Product findById(Long id) {
        final Optional<Product> searchedProduct = productRepository.findById(id);
        if (searchedProduct.isEmpty()) {
            throw new IllegalArgumentException("Product with id " + id + " is not present in the database");
        }
        return searchedProduct.get();
    }

    public List<Product> findByName(String query) {
        return unsafeProductRepository.findByName(query);
    }

    public List<Product> findAll() {
        List<Product> list = productRepository.findAll();
        if (list.isEmpty()) {
            throw new IllegalArgumentException("No products in database");
        }
        return list;
    }

}
