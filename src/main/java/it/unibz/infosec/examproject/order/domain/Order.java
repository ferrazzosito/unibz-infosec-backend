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

    private boolean isApproved;

    private String orderDocument;

    private byte[] DSA;

    public Order () {}

    public Order(Long productId, Long clientId, String orderDocument, byte[] DSA) {
        this.productId = productId;
        this.clientId = clientId;
        this.orderDocument = orderDocument;
        this.DSA = DSA;
        this.isApproved = false;
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

    public byte[] getDSA () {return DSA;}

    public String getOrderDocument () {return orderDocument;}

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
