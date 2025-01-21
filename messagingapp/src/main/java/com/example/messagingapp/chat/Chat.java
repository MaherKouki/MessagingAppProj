package com.example.messagingapp.chat;


import com.example.messagingapp.common.BaseAuditingEntity;
import com.example.messagingapp.message.Message;
import com.example.messagingapp.message.MessageConstants;
import com.example.messagingapp.message.MessageState;
import com.example.messagingapp.message.MessageType;
import com.example.messagingapp.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat")
@NamedQuery(name = ChatConstants.FIND_CHAT_BY_SENDER_ID ,
        query = "select distinct c from Chat c where c.sender.id= :senderId OR c.recipient.id= :senderId order by createdDate DESC ")

@NamedQuery(name = ChatConstants.FIND_CHAT_BY_SENDER_ID_AND_RECEIVER_ID ,
        query = "select distinct c from Chat c where (c.sender.id= :senderId and c.recipient.id= :recipientId) OR ( c.sender.id = :recipientId and c.recipient.id = :senderId )")



public class Chat extends BaseAuditingEntity {

    @Id

    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User recipient;


    @OneToMany(mappedBy = "chat" ,fetch = FetchType.EAGER)
    @OrderBy("createdDate DESC")
    private List<Message> messages;

    @Transient
    public String getChatName(final String senderId){
        if(recipient.getId().equals(senderId)){
            return sender.getFirstname() + " " + sender.getLastname();
        }
        return recipient.getFirstname() + " " + recipient.getLastname();
    }

    @Transient
    public long getUnreadMessages(final String senderId){
        return messages.stream()
                .filter(m->m.getReceiverId().equals(senderId)).
                filter(m-> MessageState.SENT == m.getState())
                .count();
    }

    @Transient
    public String getLastMessage(){
         if (!messages.isEmpty() && messages!=null){
             if(messages.get(0).getType() != MessageType.TEXTE){
                 return "ATTACHEMENT";
             }
             return messages.get(0).getContent();
         }
         return null;
    }


    @Transient
    public LocalDateTime getLastMessageTime(){
        if (!messages.isEmpty() && messages!=null){
            return messages.get(0).getCreatedDate();
        }
        return null;
    }



}
