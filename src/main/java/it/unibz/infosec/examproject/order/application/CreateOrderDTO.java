package it.unibz.infosec.examproject.order.application;

public class CreateOrderDTO {
    private Long productId;
    private Long clientId;

    public CreateOrderDTO() {}

    public CreateOrderDTO(Long productId, Long clientId) {
        this.productId = productId;
        this.clientId = clientId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
