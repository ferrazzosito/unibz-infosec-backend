package it.unibz.infosec.examproject.order.domain;

import it.unibz.infosec.examproject.product.domain.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderWithProductInfo extends Order {

    private Product product;

    public OrderWithProductInfo(Order order, Product product) {
        super(order.getProductId(), order.getVendorId(), order.getClientId(), order.getOrderDocument(), order.getDSA());
        this.id = order.getId();
        this.product = product;
    }
}
