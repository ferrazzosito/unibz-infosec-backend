package it.unibz.infosec.examproject.order.domain;

import it.unibz.infosec.examproject.user.domain.SafeUserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderWithCustomerInfo extends Order {

    private SafeUserEntity customer;

    public OrderWithCustomerInfo(Order order, SafeUserEntity customer) {
        super(order.getProductId(), order.getVendorId(), order.getClientId(), order.getOrderDocument(), order.getDSA());
        this.id = order.getId();
        this.customer = customer;
    }
}
