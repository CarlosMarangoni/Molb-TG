import { PostItem } from './../../model/postItem-dto';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessengerService {

  subject = new Subject<PostItem[]>();
  itemExists:boolean = false;
  postItems : PostItem[] = new Array<PostItem>() ;

  constructor() {
    this.subject = new BehaviorSubject<PostItem[]>(new Array<PostItem>())
   }

  sendMsg(postItem:PostItem) {
    for (let i in this.postItems) {
      if (this.postItems[i].postId === postItem.postId && this.postItems[i].item === postItem.item) {
        this.itemExists = true
        console.log("Item já existe!")
        break;
      }
    }
    if (!this.itemExists) {
      this.postItems.push(postItem)
      this.subject.next(this.postItems) //Triggering an event
      console.log("Item adicionado ao carrinho!")
    }
  }

  getMsg() {
    return this.subject;
  }


}
