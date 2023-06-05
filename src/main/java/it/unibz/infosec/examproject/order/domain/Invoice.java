package it.unibz.infosec.examproject.order.domain;

import it.unibz.infosec.examproject.product.domain.Product;
import it.unibz.infosec.examproject.user.domain.UserEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Invoice {

    private Product product;
    private UserEntity customer;
    private Date orderDate;

    public Invoice(Product product, UserEntity customer, Date orderDate) {
        this.product = product;
        this.customer = customer;
        this.orderDate = orderDate;
    }
}
