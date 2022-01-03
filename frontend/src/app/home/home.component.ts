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

  searchText:any;
  posts:PageablePostDto= new PageablePostDto();
  activePage:number = 0

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
  
  
  
  
}
