package it.unibz.infosec.examproject.product.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class ManageProducts {

    private ProductRepository productRepository;
    private SearchProducts searchProducts;

    @Autowired
    public ManageProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private Product validateProduct (Long id) {

        Optional<Product> maybeProduct = productRepository.findById(id);

        if(maybeProduct.isEmpty())
            throw new IllegalArgumentException("Product with id '" + id + "' does not exist yet!");

        return maybeProduct.get();
    }

    public Product createProduct(String name, int cost, Long vendorId) {

        //TODO: check that vendorId is valid

        return productRepository.save(new Product(name, cost, vendorId));
    }

    public Product readProduct(Long id) {

        Product product = validateProduct(id);

        return product;

    }

    public Product updateProduct (Long id, String name, int cost) {

        Product product = validateProduct(id);

        product.setName(name);
        product.setCost(cost);

        return productRepository.save(product);
    }

    public Product deleteProduct (Long id) {

        Product product = validateProduct(id);

        productRepository.delete(product);

        return product;
    }

}
