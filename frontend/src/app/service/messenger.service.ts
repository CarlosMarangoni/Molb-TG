import { PostItem } from './../../model/postItem-dto';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessengerService {

  subject = new Subject<PostItem[]>();
  cart = new Subject<number>();
  itemExists:boolean = false;
  postItems : PostItem[] = new Array<PostItem>();
  cartLength:number = 0;

  @Output() public onAddItemToCart = new EventEmitter();

  constructor() {
    this.subject = new BehaviorSubject<PostItem[]>(new Array<PostItem>())
    this.cart = new BehaviorSubject<number>(0);
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
      this.cartLength++;
      this.subject.next(this.postItems) //Triggering an event
      this.cart.next(this.cartLength)
      console.log("Item adicionado ao carrinho!")
    }
  }

  getMsg() {
    return this.subject;
  }

  getCartQty() {
    return this.cart;
  }

  resetCart(){
    this.postItems = [];
    this.cartLength = 0;
    this.subject.next(this.postItems);
    this.cart.next(this.cartLength);
  }


}
