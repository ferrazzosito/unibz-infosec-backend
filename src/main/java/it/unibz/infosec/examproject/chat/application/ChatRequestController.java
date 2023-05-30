package it.unibz.infosec.examproject.chat.application;

import it.unibz.infosec.examproject.chat.domain.ChatRequest;
import it.unibz.infosec.examproject.chat.domain.CreateChatRequestDTO;
import it.unibz.infosec.examproject.chat.domain.ManageChatRequests;
import it.unibz.infosec.examproject.user.domain.Role;
import it.unibz.infosec.examproject.user.domain.UserEntity;
import it.unibz.infosec.examproject.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/v1/chats")
public class ChatRequestController {

    private static final Logger logger = Logger.getLogger("ChatRequestController");
    private final ManageChatRequests manageChatRequests;
    private final UserRepository userRepository;

    @Autowired
    public ChatRequestController(ManageChatRequests manageChatRequests, UserRepository userRepository) {
        this.manageChatRequests = manageChatRequests;
        this.userRepository = userRepository;
    }

    @PostMapping("/request")
    public ChatRequest createNewChatRequest(@RequestBody CreateChatRequestDTO dto) {
        final User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final UserEntity loggedUserEntity =
                this.userRepository.findByEmail(loggedUser.getUsername()).orElseThrow(() ->
                        new IllegalStateException("Logged user is invalid. This should not happen."));
        return this.manageChatRequests.createChatRequest(loggedUserEntity.getId(), dto.getVendorId());
    }

    @GetMapping("/requests")
    public List<ChatRequest> getChatRequestsForVendor() {
        final User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final UserEntity loggedUserEntity =
                this.userRepository.findByEmail(loggedUser.getUsername()).orElseThrow(() ->
                        new IllegalStateException("Logged user is invalid. This should not happen."));
        if (loggedUserEntity.getRole() != Role.VENDOR) {
            throw new IllegalArgumentException("Chat requests can only be listed for users of type vendor");
        }
        return this.manageChatRequests.findChatRequestsForVendor(loggedUserEntity.getId());
    }

}
