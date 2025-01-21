package com.example.messagingapp.message;


import com.example.messagingapp.chat.Chat;
import com.example.messagingapp.common.BaseAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
@NamedQuery(name = MessageConstants.FIND_MESSAGES_BY_CHAT_ID ,
            query = "select m from Message m where m.chat.id = :chatId order by m.createdDate")

@NamedQuery(name = MessageConstants.SET_MESSAGES_TO_SEEN_BY_CHAT ,
        query = "update Message SET state = :newStat where chat.id = :chatId")

public class Message extends BaseAuditingEntity {

    @Id
    @SequenceGenerator(name = "msg_seq" , sequenceName = "msg_seq" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "msg_seq")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private MessageState state;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @Column(name = "sender_id",nullable = false)
    private String senderId;
    @Column(name = "receiver_id",nullable = false)
    private String receiverId;


}
