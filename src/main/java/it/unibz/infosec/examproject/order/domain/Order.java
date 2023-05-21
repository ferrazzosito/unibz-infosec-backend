package it.unibz.infosec.examproject.order.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "managed_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long clientId;

    public Order () {}

    public Order(Long productId, Long clientId) {
        this.productId = productId;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getClientId() {
        return clientId;
    }
}
