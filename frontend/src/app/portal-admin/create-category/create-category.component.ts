import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PostService } from 'src/app/service/post.service';
import { Categoria } from 'src/model/category-dto';

@Component({
  selector: 'app-create-category',
  templateUrl: './create-category.component.html',
  styles: [
  ]
})
export class CreateCategoryComponent implements OnInit {

  public form:any = {};

  constructor(private location:Location,private modalService: NgbModal,private postService:PostService,private router:Router) { }

  ngOnInit(): void {
  }

  backClicked(){
    this.location.back();
  }

  onSubmit(f:NgForm,content:any){
    let categoria = new Categoria(this.form.categoryName);
    this.postService.cadastrarCategoria(categoria).subscribe(data=>{
      console.log(data)
      this.modalService.open(content,
        {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
           this.router.navigateByUrl(`/admin/category`);
         });
    },error=>console.log(error))

    f.resetForm()
    
  }

}
