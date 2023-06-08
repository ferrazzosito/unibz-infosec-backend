package it.unibz.infosec.examproject.order.application;

public class CreateOrderDTO {

    private Long productId;

    public CreateOrderDTO() {}

    public CreateOrderDTO(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
