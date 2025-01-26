import {Component, input, InputSignal, output} from '@angular/core';
import {ChatResponse} from "../../services/models/chat-response";
import {DatePipe} from "@angular/common";
import {UserResponse} from "../../services/models/user-response";
import {UserControllerService} from "../../services/services/user-controller.service";

@Component({
  selector: 'app-chat-list',
  imports: [
    DatePipe
  ],
  templateUrl: './chat-list.component.html',
  styleUrls: ['./chat-list.component.scss'] // Fixed typo: styleUrl -> styleUrls
})
export class ChatListComponent {
  chats: InputSignal<ChatResponse[]> = input<ChatResponse[]>([]);
  searchNewContact = false;
  contacts: Array<UserResponse> = [];
  chatSelected=output <ChatResponse>();

  constructor(
    private userService: UserControllerService
  ) {}

  searchContact(): void {
    this.userService.getUsers().subscribe({
      next: (users: Array<UserResponse>) => {
        this.contacts = users;
        this.searchNewContact = true;
      },
      error: (err) => {
        console.error('Failed to fetch users', err); // Optional: handle errors
      }
    });
  }

  chatClicked(chat: ChatResponse): void {
    // Implement chat click functionality here
  }

  wrapMessage(lastMessage: string | undefined): string {
    if (lastMessage && lastMessage.length <= 20) {
      return lastMessage;
    }
    return lastMessage?.substring(0, 17) + '...';
  }

  protected readonly getSelection = getSelection;

  selectContact(contact: UserResponse): void { // Fixed typo: concact -> contact
    // Implement contact selection functionality here
  }
}
