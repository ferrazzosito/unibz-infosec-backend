package it.unibz.infosec.examproject.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unibz.infosec.examproject.user.domain.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany
    private List<Message> messages = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "vendor")
    private User vendor;

    @OneToOne
    @JoinColumn(name = "customer")
    private User customer;

    public Long getId() {
        return id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public User getVendor() {
        return vendor;
    }

    public User getCustomer() {
        return customer;
    }
}
