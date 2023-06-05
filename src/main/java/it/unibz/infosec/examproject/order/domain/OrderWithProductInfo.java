package it.unibz.infosec.examproject.order.domain;

import it.unibz.infosec.examproject.product.domain.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderWithProductInfo extends Order {

    private Product product;

    public OrderWithProductInfo(Long productId, Long vendorId, Long clientId, String orderDocument, byte[] DSA, Product product) {
        super(productId, vendorId, clientId, orderDocument, DSA);
        this.product = product;
    }
}
