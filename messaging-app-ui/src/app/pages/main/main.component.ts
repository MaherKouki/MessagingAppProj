import {Component, OnInit} from '@angular/core';
import {ChatListComponent} from "../../components/chat-list/chat-list.component";
import {ChatResponse} from "../../services/models/chat-response";
import {ChatControllerService} from "../../services/services/chat-controller.service";
import {KeycloakService} from "../../utils/keycloak/keycloak.service";
import {MessageControllerService} from '../../services/services/message-controller.service';
import {MessageResponse} from '../../services/models/message-response';

@Component({
  selector: 'app-main',
  imports: [
    ChatListComponent
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent implements OnInit {

  chats: Array<ChatResponse> = [];
  selectedChat: ChatResponse ={};
  chatMessages: MessageResponse[] = [];


  constructor(
    private chatService : ChatControllerService,
    private keycloakService : KeycloakService,
    private messageService: MessageControllerService
  ) {
  }

    ngOnInit(): void {
    this.getAllChats()
    }

    private getAllChats(): void {
    this.chatService.getChatsByReceiverId()
      .subscribe({
        next: (res =>
        this.chats = res)
      })
    }


  logout() {
    this.keycloakService.logout();

  }

  userProfile() {
    this.keycloakService.accountManagement();

  }

  chatSelected(chatResponse: ChatResponse) {
    this.selectedChat = chatResponse;
    this.getAllChatMessages(chatResponse.id as string);
    this.setMessagesToSeen();
    //this.selectedChat.unreadCount = 0;
  }

  private getAllChatMessages(chatId: string) {
    this.messageService.getMessages({
      'chat-id': chatId
    }).subscribe({
      next: (message) =>{
        this.chatMessages = message;
      }
    })
  }


  private setMessagesToSeen() {

  }
}
