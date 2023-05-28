package it.unibz.infosec.examproject.chat.domain;

import it.unibz.infosec.examproject.user.domain.ManageUsers;
import it.unibz.infosec.examproject.user.domain.RoleRepository;
import it.unibz.infosec.examproject.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ManageChatRequests {

    private final ChatRequestRepository chatRequestRepository;
    private final RoleRepository roleRepository;
    private final ManageUsers manageUsers;

    @Autowired
    public ManageChatRequests(ChatRequestRepository chatRequestRepository, RoleRepository roleRepository, ManageUsers manageUsers) {
        this.chatRequestRepository = chatRequestRepository;
        this.roleRepository = roleRepository;
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
        final UserEntity customer = this.manageUsers.readUser(customerId);
        Logger.getLogger("ManageChatRequest").log(Level.INFO, "customer = " + customer);
        final UserEntity vendor = this.manageUsers.readUser(vendorId);
        if (!customer.getRoles().contains(this.roleRepository.findByName("CLIENT").get())) {
            throw new IllegalArgumentException("Chat requests can only be issued by customers");
        }
        if (!vendor.getRoles().contains(this.roleRepository.findByName("VENDOR").get())) {
            throw new IllegalArgumentException("Chat requests can only be issued towards vendors");
        }
        return this.chatRequestRepository.save(new ChatRequest(
                this.manageUsers.readUser(vendorId),
                this.manageUsers.readUser(customerId)
        ));
    }

    public List<ChatRequest> findChatRequestsForVendor(Long vendorId) {
        return this.chatRequestRepository.findByVendorId(vendorId);
    }

    public ChatRequest deleteChatRequest(String id) {
        final ChatRequest chatRequest = validateChatRequest(id);
        this.chatRequestRepository.delete(chatRequest);
        return chatRequest;
    }

}
