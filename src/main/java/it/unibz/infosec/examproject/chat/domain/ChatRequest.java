package it.unibz.infosec.examproject.chat.domain;

import it.unibz.infosec.examproject.user.domain.User;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class ChatRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "vendor")
    private User vendor;

    @OneToOne
    @JoinColumn(name = "customer")
    private User customer;

    private String chatId;

    public ChatRequest() {
    }

    public ChatRequest(User vendor, User customer) {
        this.vendor = vendor;
        this.customer = customer;
        this.chatId = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public User getVendor() {
        return vendor;
    }

    public User getCustomer() {
        return customer;
    }

    public String getChatId() {
        return chatId;
    }
}
