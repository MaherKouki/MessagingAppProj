import {Component, OnInit} from '@angular/core';
import {ChatListComponent} from "../../components/chat-list/chat-list.component";
import {ChatResponse} from "../../services/models/chat-response";
import {ChatControllerService} from "../../services/services/chat-controller.service";

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
    private chatService : ChatControllerService
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


}
