package it.unibz.infosec.examproject.chat.domain.message;

public class WelcomeMessage extends ControlMessage {

    private final int subscribers;

    public WelcomeMessage(int subscribers) {
        super("welcome");
        this.subscribers = subscribers;
    }
}
