package com.example.messagingapp.message;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageRequest {

    private String content;
    private String receiverId;
    private String senderId;
    private MessageType type;
    private String chatId;
}
