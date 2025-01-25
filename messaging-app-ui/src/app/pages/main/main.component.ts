import {Component, OnInit} from '@angular/core';
import {ChatListComponent} from "../../components/chat-list/chat-list.component";
import {ChatResponse} from "../../services/models/chat-response";
import {ChatControllerService} from "../../services/services/chat-controller.service";
import {KeycloakService} from "../../utils/keycloak/keycloak.service";

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
  constructor(
    private chatService : ChatControllerService,
    private keycloakService : KeycloakService
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
}
