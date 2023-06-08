package it.unibz.infosec.examproject.order.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "managed_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private Long productId;

    private Long vendorId;

    private Long clientId;

    private boolean isApproved;

    private String orderDocument;

    private byte[] DSA;

    public Order() {}

    public Order(Long productId, Long vendorId, Long clientId, String orderDocument, byte[] DSA) {
        this.productId = productId;
        this.vendorId = vendorId;
        this.clientId = clientId;
        this.orderDocument = orderDocument;
        this.DSA = DSA;
        this.isApproved = false;
    }

    public Order(Long productId, Long vendorId, Long clientId, String orderDocument, byte[] DSA, boolean isApproved) {
        this.productId = productId;
        this.vendorId = vendorId;
        this.clientId = clientId;
        this.orderDocument = orderDocument;
        this.DSA = DSA;
        this.isApproved = isApproved;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public Long getClientId() {
        return clientId;
    }

    public byte[] getDSA() {
        return DSA;
    }

    public String getOrderDocument() {
        return orderDocument;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
