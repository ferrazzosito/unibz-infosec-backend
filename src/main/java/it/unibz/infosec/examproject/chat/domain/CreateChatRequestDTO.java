package it.unibz.infosec.examproject.chat.domain;

public class CreateChatRequestDTO {

    private Long customerId;
    private Long vendorId;

    public CreateChatRequestDTO() {
    }

    public CreateChatRequestDTO(Long customerId, Long vendorId) {
        this.customerId = customerId;
        this.vendorId = vendorId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
