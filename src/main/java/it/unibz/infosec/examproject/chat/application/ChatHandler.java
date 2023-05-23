package it.unibz.infosec.examproject.chat.application;

import it.unibz.infosec.examproject.chat.domain.ManageChatRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ChatHandler extends TextWebSocketHandler {

    private final Map<String, List<WebSocketSession>> sessions = new HashMap<>();
    @Autowired
    private ManageChatRequests manageChatRequests;

    public ChatHandler() {
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        final String chatId;
        try {
            chatId = ensureValidChatId(session);
        } catch (Exception e) {
            session.close();
            return;
        }
        List<WebSocketSession> sockets = this.sessions.get(chatId);
        if (sockets == null) {
            sockets = new ArrayList<>();
        }
        sockets.add(session);
        this.sessions.put(chatId, sockets);
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
        final String chatId;
        try {
            chatId = ensureValidChatId(session);
        } catch (Exception e) {
            return;
        }
        final List<WebSocketSession> sockets = this.sessions.get(chatId);
        if (sockets == null || !sockets.contains(session)) {
            return;
        }
        sockets.remove(session);
        if (sockets.isEmpty()) {
            this.sessions.remove(chatId);
        } else {
            this.sessions.put(chatId, sockets);
        }
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception {
        final String chatId;
        try {
            chatId = ensureValidChatId(session);
        } catch (Exception e) {
            session.close();
            return;
        }
        final List<WebSocketSession> sockets = this.sessions.get(chatId);
        for (final WebSocketSession recipient : sockets) {
            if (!session.equals(recipient)) {
                recipient.sendMessage(message);
            }
        }
    }

    private String ensureValidChatId(@NonNull WebSocketSession session) throws Exception {
        final String chatId = (String) session.getAttributes().get("chat_id");
        if (chatId == null) {
            session.close();
            throw new IllegalStateException("No chat id associated with the given WebSocketSession.");
        }
        return this.manageChatRequests.findByChatId(chatId).getChatId();
    }
}
