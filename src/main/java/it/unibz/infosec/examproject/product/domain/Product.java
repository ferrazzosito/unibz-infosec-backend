package it.unibz.infosec.examproject.product.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int cost;

    private Long vendorId;

    public Product () {}

    public Product(Long id, String name, int cost, Long vendorId) {
        this(name, cost, vendorId);
        this.id = id;
    }

    public Product(String name, int cost, Long vendorId) {
        this.name = name;
        this.cost = cost;
        this.vendorId = vendorId;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Long getVendorId() {
        return vendorId;
    }
}
