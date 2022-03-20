import { PostItem } from './../../model/postItem-dto';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessengerService {

  subject = new Subject<PostItem[]>();

  postItems : PostItem[] = new Array<PostItem>() ;

  constructor() {
    this.subject = new BehaviorSubject<PostItem[]>(new Array<PostItem>())
   }

  sendMsg(postItem:PostItem) {
    this.postItems.push(postItem)
    this.subject.next(this.postItems) //Triggering an event
  }

  getMsg() {
    return this.subject;
  }


}
