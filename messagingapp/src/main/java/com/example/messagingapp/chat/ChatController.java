package com.example.messagingapp.chat;


import com.example.messagingapp.common.StringResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;


    @PostMapping
    public ResponseEntity <StringResponse> createdChat(
            @RequestParam(name = "sender-Id") String senderId,
            @RequestParam(name = "receiver-Id") String receiverId){

        final String chatId = chatService.createChat(senderId,receiverId);

        StringResponse stringResponse = StringResponse.builder()
                .response(chatId)
                .build();
        return ResponseEntity.ok(stringResponse);
    }
}
