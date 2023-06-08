package it.unibz.infosec.examproject.chat.domain;

public class CreateChatRequestDTO {

    private Long vendorId;

    public CreateChatRequestDTO() {
    }

    public CreateChatRequestDTO(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getVendorId() {
        return vendorId;
    }
}
