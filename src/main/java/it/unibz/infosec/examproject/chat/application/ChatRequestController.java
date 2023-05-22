package it.unibz.infosec.examproject.chat.application;

import it.unibz.infosec.examproject.chat.domain.ChatRequest;
import it.unibz.infosec.examproject.chat.domain.CreateChatRequestDTO;
import it.unibz.infosec.examproject.chat.domain.ManageChatRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/chats")
public class ChatRequestController {

    private final ManageChatRequests manageChatRequests;

    @Autowired
    public ChatRequestController(ManageChatRequests manageChatRequests) {
        this.manageChatRequests = manageChatRequests;
    }

    @PostMapping("/request")
    public ChatRequest createNewChatRequest(@RequestBody CreateChatRequestDTO dto) {
        return this.manageChatRequests.createChatRequest(dto.getCustomerId(), dto.getVendorId());
    }

    @GetMapping("/requests")
    public List<ChatRequest> getChatRequestsForVendor() {
        return this.manageChatRequests.findChatRequestsForVendor(0L);
    }

}
