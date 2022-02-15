import { Router } from '@angular/router';
import { UserForm } from './../../model/user-form';
import { UserService } from './../service/user.service';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {NgbModal,ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styles: [
  ]
})

export class RegisterComponent implements OnInit {
  hide=true;
  public uploadedImageUrl:String = "/assets/img/no-image.png";
  public file: File | undefined;
  private userForm:UserForm = new UserForm();
  form: any = {};
  public uploaded = false;
  public message:string = "";
  public addUserSuccess:boolean = false;
  closeResult = '';

  constructor(private userService:UserService,private router:Router,private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  
  onChange(event: any) {
    const selectedFile = <FileList>event.srcElement.files;
    const fileLabel = document.getElementById("customFileLabel");
    if (!fileLabel) return;
    const reader = new FileReader();
    reader.addEventListener('load', () => {
      this.uploadedImageUrl=reader.result!.toString();
    });
    reader.readAsDataURL(selectedFile[0]);
    fileLabel.innerHTML = selectedFile[0].name;
    this.file = selectedFile[0];
  }

  onSubmit(f:any,content:any){
    this.userForm.email= this.form.email;
    this.userForm.description= this.form.description;
    this.userForm.username= this.form.username;
    this.userForm.name= this.form.name;
    if(this.form.password != this.form.confirmPassword){
      this.message = "Senhas não correspondem."
      this.form.password = "";
      this.form.confirmPassword = "";
    }else{
      this.userForm.password = this.form.password;
      if(this.file){
        this.userService.cadastrarUsuarioComArquivo(this.userForm,this.file!).subscribe(data=>{
          console.log(data)
        },error =>console.log(error.error.message))
      }else{
        this.userService.cadastrarUsuario(this.userForm).subscribe(data=>{
          console.log(data)
        },error=>console.log(error.error.message))
      }
      f.resetForm();
      this.addUserSuccess = true;
      this.modalService.open(content,
        {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
           this.router.navigateByUrl('/login');
         });
    }

    //this.userService.cadastrarUsuario()
  }
  
}
