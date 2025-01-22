package com.example.messagingapp.chat;

import org.springframework.stereotype.Service;

@Service
public class ChatMapper {

    public ChatResponse toChatResponse(Chat chat, String senderId){
        return ChatResponse.builder()
                .id(chat.getId())
                .name(chat.getChatName(senderId))
                .lastMessage(chat.getLastMessage())
                .unreadCount(chat.getUnreadMessages(senderId))
                .isRecipientOnline(chat.getRecipient().isUserOnline())
                .senderId(chat.getSender().getId())
                .receiverId(chat.getRecipient().getId())
                .build();
    }
}
