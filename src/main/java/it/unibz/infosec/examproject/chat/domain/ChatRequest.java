package it.unibz.infosec.examproject.chat.domain;

import it.unibz.infosec.examproject.user.domain.UserEntity;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class ChatRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "vendor")
    private UserEntity vendor;

    @OneToOne
    @JoinColumn(name = "customer")
    private UserEntity customer;

    private String chatId;

    public ChatRequest() {
    }

    public ChatRequest(UserEntity vendor, UserEntity customer) {
        this.vendor = vendor;
        this.customer = customer;
        this.chatId = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public UserEntity getVendor() {
        return vendor;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public String getChatId() {
        return chatId;
    }
}
