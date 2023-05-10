package it.unibz.infosec.examproject.product.application;

public class CreateProductDTO {

    private String name;
    private int cost;
    private Long vendorId;

    public CreateProductDTO() {}

    public CreateProductDTO(String name, int cost, Long vendorId) {
        this.name = name;
        this.cost = cost;
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
}
