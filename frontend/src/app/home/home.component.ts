import { Categoria } from './../../model/category-dto';
import { TokenStorageService } from './../service/token-storage.service';
import { PageablePostDto } from './../../model/pageable-post-dto';
import { PostService } from './../service/post.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: [
  ]
})
export class HomeComponent implements OnInit{

  filtro:string='';
  posts:PageablePostDto= new PageablePostDto();
  activePage:number = 0;
  selectedIndex: number = 0;
  selectedSearch:any = 'userFilter';
  categories:Categoria[] = []; 
  roles: string[] = []; 
  authority: string = "";

  constructor(private postService:PostService,private token: TokenStorageService){
    this.postService=postService;
  }
  
  ngOnInit(): void {
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
    this.postService.obterTodasPostagensPaginadas(this.activePage)
    .subscribe(posts =>{
      this.posts = posts;
    },
    error => console.log(error))
    this.obterCategorias();
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
        if(v.title.toLowerCase().indexOf(this.filtro.toLowerCase()) >= 0){
          return true;
        }
        return false;
      })
    }
   
  }

  obterCategorias(){
    this.postService.obterTodasCategorias().subscribe(categories =>{
      this.categories = categories;
      this.categories.unshift(new Categoria("TODOS"));
    },error => console.log(error))
  }

  setIndex(index: number) {
    this.selectedIndex = index;
    if(index!=0){
      this.postService.obterPostsPorCategoria(this.categories[index].name.toLowerCase()).subscribe(posts =>{
        this.posts = posts;
        console.log(posts)
      },error => console.log(error))
    }else{
      this.postService.obterTodasPostagensPaginadas(this.activePage)
    .subscribe(posts =>{
      this.posts = posts;
      console.log(posts)
    },
    error => console.log(error))
    }
 }
  
    
}
