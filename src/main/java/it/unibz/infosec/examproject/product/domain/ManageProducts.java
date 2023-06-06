package it.unibz.infosec.examproject.product.domain;

import it.unibz.infosec.examproject.order.domain.OrderRepository;
import it.unibz.infosec.examproject.review.domain.ReviewRepository;
import it.unibz.infosec.examproject.user.domain.ManageUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManageProducts {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;
    private SearchProducts searchProducts;
    private final ManageUsers manageUsers;

    @Autowired
    public ManageProducts(ProductRepository productRepository, ManageUsers manageUsers, OrderRepository orderRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.reviewRepository = reviewRepository;
        this.manageUsers = manageUsers;
    }

    private Product validateProduct(Long id) {
        final Optional<Product> maybeProduct = productRepository.findById(id);
        if (maybeProduct.isEmpty())
            throw new IllegalArgumentException("Product with id '" + id + "' does not exist yet!");
        return maybeProduct.get();
    }

    public Product createProduct(String name, int cost, Long vendorId) {
        return productRepository.save(new Product(name, cost, manageUsers.readUser(vendorId).getId()));
    }

    public Product readProduct(Long id) {
        return validateProduct(id);
    }

    public Product updateProduct(Long id, Long vendorId, String name, int cost) {
        final Product product = validateProduct(id);
        if (!vendorId.equals(product.getVendorId())) {
            throw new IllegalArgumentException("Only the owner of a product can update it");
        }
        product.setName(name);
        product.setCost(cost);
        return productRepository.save(product);
    }

    public Product deleteProduct(Long id, Long vendorId) {
        final Product product = validateProduct(id);

        if (!orderRepository.findByProductId(product.getId()).isEmpty() ||
                !reviewRepository.findByProductId(product.getId()).isEmpty()) {
            throw new IllegalArgumentException("Cannot remove product with orders or reviews linked to it");
        }

        if (!vendorId.equals(product.getVendorId())) {
            throw new IllegalArgumentException("Only the owner of a product can delete it");
        }
        productRepository.delete(product);
        return product;
    }

    public List<Product> getByVendor(Long vendorId) {
        return productRepository.findByVendorId(vendorId);
    }
}
