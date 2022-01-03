import { PageablePostDto } from './../../model/pageable-post-dto';
import { PostService } from './../service/post.service';
import { Post } from './../../model/post-dto';
import { User } from './../../model/user-dto';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: [
  ]
})
export class HomeComponent implements OnInit{

  filtro:string=''
  posts:PageablePostDto= new PageablePostDto();
  activePage:number = 0
  selectedSearch:any = 'userFilter'

  constructor(private postService:PostService){
    this.postService=postService;
  }
  
  ngOnInit(): void {
    this.postService.obterTodasPostagensPaginadas(this.activePage)
    .subscribe(posts =>{
      this.posts = posts;
      console.log(posts)
    },
    error => console.log(error))
  }

  onClick(activePage:number){
    this.activePage=activePage;
    this.postService.obterTodasPostagensPaginadas(this.activePage)
    .subscribe(posts =>{
      this.posts = posts;
      console.log(posts)
    },
    error => console.log(error))
  }

  obterPosts(){
    if(this.posts.content.length === 0 || this.filtro.trim() === ''){
      return this.posts.content;
    }

    if(this.selectedSearch == 'userFilter'){
      return this.posts.content.filter((v) => {
        if(v.user.toLowerCase().indexOf(this.filtro.toLowerCase()) >= 0){
          return true;
        }
        return false;
      })
    }else {
      return this.posts.content.filter((v) => {
        if(v.description.toLowerCase().indexOf(this.filtro.toLowerCase()) >= 0){
          return true;
        }
        return false;
      })
    }
   
  }
    
}
