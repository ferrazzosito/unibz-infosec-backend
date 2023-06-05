package it.unibz.infosec.examproject.order.application;

import lombok.Data;

@Data
public class ApproveOrderResponseDTO {

    private boolean success;
    private Long orderId;

    public ApproveOrderResponseDTO(boolean success, Long orderId) {
        this.success = success;
        this.orderId = orderId;
    }
}
