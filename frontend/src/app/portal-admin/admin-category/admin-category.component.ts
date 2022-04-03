import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/service/post.service';
import { Categoria } from 'src/model/category-dto';

@Component({
  selector: 'app-admin-category',
  templateUrl: './admin-category.component.html',
  styles: [
  ]
})
export class AdminCategoryComponent implements OnInit {

  public categories:Categoria[] = []

  constructor(private postService:PostService) { }

  ngOnInit(): void {
    this.postService.obterTodasCategorias().subscribe(data=>{
      this.categories = data;
      console.log(data)
    },error => console.log(error))
  }

  newCategory(){

  }

  editCategory(){
    
  }

}
