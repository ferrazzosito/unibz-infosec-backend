package it.unibz.infosec.examproject.order.application;

public class UpdateOrderDTO extends CreateOrderDTO {

    private Long id;

    public UpdateOrderDTO () {
        super();
    }

    public UpdateOrderDTO(Long id, Long productId) {
        super(productId);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
