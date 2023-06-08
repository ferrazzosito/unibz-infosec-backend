package it.unibz.infosec.examproject.chat.domain.message;

public class ErrorMessage extends ControlMessage {

    private final String message;
    private final String code;

    public ErrorMessage(String code, String message) {
        super("error");
        this.code = code;
        this.message = message;
    }
}
