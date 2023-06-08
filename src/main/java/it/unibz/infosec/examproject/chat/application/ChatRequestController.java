package it.unibz.infosec.examproject.chat.application;

import it.unibz.infosec.examproject.chat.domain.ChatRequest;
import it.unibz.infosec.examproject.chat.domain.CreateChatRequestDTO;
import it.unibz.infosec.examproject.chat.domain.ManageChatRequests;
import it.unibz.infosec.examproject.user.domain.Role;
import it.unibz.infosec.examproject.user.domain.UserEntity;
import it.unibz.infosec.examproject.user.domain.UserRepository;
import it.unibz.infosec.examproject.util.RESTUtils;
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
        return this.manageChatRequests.createChatRequest(
                RESTUtils.getLoggedUser(userRepository).getId(), dto.getVendorId());
    }

    @GetMapping("/requests")
    public List<ChatRequest> getChatRequestsForVendor() {
        final UserEntity loggedUser = RESTUtils.getLoggedUser(userRepository);
        if (loggedUser.getRole() != Role.VENDOR) {
            throw new IllegalArgumentException("Chat requests can only be listed for users of type vendor");
        }
        return this.manageChatRequests.findChatRequestsForVendor(loggedUser.getId());
    }

}
