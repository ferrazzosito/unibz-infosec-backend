package it.unibz.infosec.examproject.chat.domain;

import it.unibz.infosec.examproject.user.domain.ManageUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ManageChatRequests {

    private final ChatRequestRepository chatRequestRepository;
    private final ManageUsers manageUsers;

    @Autowired
    public ManageChatRequests(ChatRequestRepository chatRequestRepository, ManageUsers manageUsers) {
        this.chatRequestRepository = chatRequestRepository;
        this.manageUsers = manageUsers;
    }

    private ChatRequest validateChatRequest(Long id) {
        final Optional<ChatRequest> maybeChatRequest = this.chatRequestRepository.findById(id);
        if (maybeChatRequest.isEmpty()) {
            throw new IllegalArgumentException("Chat request with id '" + id + "' does not exist yet!");
        }
        return maybeChatRequest.get();
    }

    private ChatRequest validateChatRequest(String chatId) {
        final List<ChatRequest> chatRequests = this.chatRequestRepository.findByChatId(chatId);
        if (chatRequests.isEmpty()) {
            throw new IllegalArgumentException("Chat request with chatId '" + chatId + "' does not exist yet!");
        }
        return chatRequests.get(0);
    }

    public ChatRequest findByChatId(String chatId) {
        return validateChatRequest(chatId);
    }

    public ChatRequest createChatRequest(Long customerId, Long vendorId) {
        return this.chatRequestRepository.save(new ChatRequest(
                this.manageUsers.readUser(vendorId),
                this.manageUsers.readUser(customerId)
        ));
    }

    public List<ChatRequest> findChatRequestsForVendor(Long vendorId) {
        return this.chatRequestRepository.findByVendor(vendorId);
    }

    public ChatRequest deleteChatRequest(String id) {
        final ChatRequest chatRequest = validateChatRequest(id);
        this.chatRequestRepository.delete(chatRequest);
        return chatRequest;
    }

}
