import { MessengerService } from './../service/messenger.service';
import { TokenStorageService } from './../service/token-storage.service';
import { NgForm } from '@angular/forms';
import { CommentForm } from './../../model/comment-form';
import { Post } from './../../model/post-dto';
import { ActivatedRoute } from '@angular/router';
import { PostService } from './../service/post.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styles: [
  ]
})
export class PostDetailComponent implements OnInit {

  public id: number = 0;
  public post:Post = new Post();
  public comment:any = "";
  public stars:number = 1;
  public owner: boolean = false;
  public message:string = '';
  public hasBought = false;
  public roles: string[] = [];
  public authority: string = "";

  constructor(private postService:PostService,private route: ActivatedRoute,private token:TokenStorageService,private msg:MessengerService) { 
    this.postService = postService;
  }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.postService.obterPostagem(this.id).subscribe(p => {
      this.post = p;
      if (p.userId === Number(this.token.getUserId())){
        this.owner = true; 
        
      }
      console.log(p)
    },
    error => console.log(error));
    
    if (this.token.getToken()) {
      this.roles = this.token.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        } else if (role === 'ROLE_CREATOR') {
          this.authority = 'creator';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
}

  
  onSubmit(f:NgForm):any{
  const commentForm = new CommentForm(this.comment,this.stars);
  this.postService.comentarPostagem(this.id,commentForm).subscribe(p =>{
    this.post= p;
  },
  error => this.message = error.error.message)

  this.comment = '';
   
  }

  onValueChange($event: number) {
    this.stars = $event
  }

  addToCart(postItem:any){
    this.msg.sendMsg(postItem)
  }

}
