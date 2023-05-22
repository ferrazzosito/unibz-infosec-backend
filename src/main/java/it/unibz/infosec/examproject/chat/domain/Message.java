package it.unibz.infosec.examproject.chat.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Chat chat;

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Chat getChat() {
        return chat;
    }
}
