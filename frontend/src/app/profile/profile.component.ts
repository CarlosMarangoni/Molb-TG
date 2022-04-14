import { TokenStorageService } from './../service/token-storage.service';
import { PostService } from './../service/post.service';
import { UserService } from './../service/user.service';
import { Post } from './../../model/post-dto';
import { User } from './../../model/user-dto';
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FollowerDto } from 'src/model/follower-dto';
import { FollowingDto } from 'src/model/following-dto';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styles: [
  ]
})
export class ProfileComponent implements OnInit {

  public id: number = 0;
  public user:User = new User();
  public loggedUser:User = new User();
  public followersQtd:number = 0;
  public followersArray:FollowerDto[] = [];
  public followingArray:FollowingDto[] = [];
  public followingQtd:number = 0;
  public postCount:number = 0;
  public posts:Post[] = [];
  authority: string[] = [];
  roles: string[] = [];
  public owner: boolean = false;
  public profileOwner:boolean = false;
  public canEditDesc: boolean = false;
  public userFollows:boolean = false;
  

  constructor(private route: ActivatedRoute,private userService:UserService,private router:Router,
    private postService:PostService,private token:TokenStorageService,private modalService: NgbModal) {
    this.route=route;
    this.userService = userService;
    
   }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.userService.obterUsuario(this.id).subscribe(user =>{
    this.user = user;      
    this.followersQtd = this.user.followers.length;
    this.followingQtd = this.user.following.length;
    this.postCount = this.user.posts.length;
    this.postService.obterPostsdeUsuario(this.id).subscribe(post =>{
      this.posts = post
    },
    error => console.log(error))
    },
    error => console.log(error));
   
    this.userService.obterUsuario(Number(this.token.getUserId())).subscribe(user =>{
      this.userFollows = user.following.filter(f =>f.user === this.user.user).length > 0 
    },error => console.log(error))   
    

    if (this.token.getToken()) {
      this.roles = this.token.getAuthorities();
      this.roles.forEach(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority.push('admin');
        } else if (role === 'ROLE_CREATOR') {
          this.authority.push('creator');
        }
        this.authority.push('user');;
      });
    }
    if (this.id === Number(this.token.getUserId())){
      this.profileOwner = true;
    }

    if (this.id === Number(this.token.getUserId()) && this.authority.includes('creator')){
      this.owner = true;
  }

  }

  saveEditDesc(){
    const description = this.user.description
    this.userService.atualizarDescricao(this.id,description).subscribe(user =>{
      this.user.description = user.description
    })
    this.canEditDesc = false;
  }

  followUser(){
    this.userService.seguirUsuario(this.id).subscribe(user =>{
      if(this.userFollows){
        this.userFollows = false;
        this.followersQtd--
      }else{
        this.userFollows = true;
        this.followersQtd++
      }
    });
  }  

  showFollowers(content:any){
    this.modalService.open(content,
      {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
         this.router.navigateByUrl(`/admin/users`);
       });

    this.followersArray = this.user.followers
  }

  showFollowing(content:any){
    this.modalService.open(content,
      {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
         this.router.navigateByUrl(`/admin/users`);
       });

    this.followingArray = this.user.following;
  }

  }

  
