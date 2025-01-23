package com.example.messagingapp.chat;


import com.example.messagingapp.common.StringResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        StringResponse response = StringResponse.builder()
                .response(chatId)
                .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity <List<ChatResponse>> getChatsByReceiverId(Authentication authentication){
        return ResponseEntity.ok(chatService.getChatsByReceiverId(authentication));
    }


}
