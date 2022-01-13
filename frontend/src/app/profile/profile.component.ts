import { TokenStorageService } from './../service/token-storage.service';
import { PostService } from './../service/post.service';
import { UserService } from './../service/user.service';
import { Post } from './../../model/post-dto';
import { User } from './../../model/user-dto';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styles: [
  ]
})
export class ProfileComponent implements OnInit {

  public id: number = 0;
  public user:User = new User();
  public followers:number = 0;
  public following:number = 0;
  public postCount:number = 0;
  public posts:Post[] = [];
  authority: string = "";
  roles: string[] = [];
  public owner: boolean = false;
  

  constructor(private route: ActivatedRoute,private userService:UserService,
    private postService:PostService,private token:TokenStorageService) {
    this.route=route;
    this.userService = userService;
   }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.userService.obterUsuario(this.id).subscribe(user =>{
    this.user = user;      
    this.followers = this.user.followers.length;
    this.following = this.user.following.length;
    this.postCount = this.user.posts.length;
    console.log(user)
    this.postService.obterPostsdeUsuario(this.id).subscribe(post =>{
      this.posts = post
    },
    error => console.log(error))
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
    if (this.id === Number(this.token.getUserId()) && this.authority === 'creator'){
      this.owner = true;
  }

  }
    // this.postCount = this.posts.forEach(p=>{
    //   if(p.userId == this.id)
    // })
  }

  
