package it.unibz.infosec.examproject.order.domain;

import it.unibz.infosec.examproject.product.domain.Product;
import it.unibz.infosec.examproject.user.domain.SafeUserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderWithCustomerAndProductInfo extends Order {

    private SafeUserEntity customer;
    private Product product;

    public OrderWithCustomerAndProductInfo(Order order, SafeUserEntity customer, Product product) {
        super(order.getProductId(), order.getVendorId(), order.getClientId(), order.getOrderDocument(), order.getDSA());
        this.id = order.getId();
        this.customer = customer;
        this.product = product;
    }
}
