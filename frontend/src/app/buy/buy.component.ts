import { MessengerService } from './../service/messenger.service';
import { PostItem } from './../../model/postItem-dto';
import { TokenStorageService } from './../service/token-storage.service';
import { Post } from './../../model/post-dto';
import { PostService } from './../service/post.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styles: [
  ]
})
export class BuyComponent implements OnInit {

  public post:Post = new Post();
  public id:number = 0;
  public owner:boolean = false;
  public cartList:Array<PostItem> = [];

  constructor(private route: ActivatedRoute,private postService:PostService,private token:TokenStorageService,private msg:MessengerService) { }

  ngOnInit(): void {
    
    this.msg.getMsg().subscribe(item => {
      this.cartList=item
      
    },
    error => console.log(error))
  
  console.log(this.cartList)
  }



}
