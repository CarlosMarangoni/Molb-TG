import { Role } from './../../../model/role-dto';
import { UserService } from './../../service/user.service';
import { User } from 'src/model/user-dto';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { RoleSummary } from 'src/model/role-summary-dto';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';
import { MessageDto } from 'src/model/message-dto';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styles: [
  ]
})
export class EditUserComponent implements OnInit {

  private userId:number = 0;
  public user:User = new User();
  public roles:Role[] = [];
  public form:any = {};
  constructor(private route: ActivatedRoute,private userService:UserService,private modalService: NgbModal,
    private router:Router,private location: Location) { }

  ngOnInit(): void {
    this.userId=Number(this.route.snapshot.paramMap.get('id'));
    this.userService.obterUsuario(this.userId).subscribe(data =>{
      this.user= data;
      console.log(data);
    },error => console.log(error))

    this.userService.obterPermissoes().subscribe(data =>{
      this.roles = data;
    },error => console.log(error))
  }

  addPermission(){
    let userPermissions = this.user.permissions;

    if(!userPermissions.includes(this.form.role)){
      this.user.permissions.push(this.form.role)      
    }
  }

  removePermission(i:number){
    this.user.permissions.splice(i,1);
  }

  onSubmit(f:NgForm,content:any){
    console.log(this.user.permissions)
    var roles = new RoleSummary();
    roles.permissions = this.user.permissions;
    this.userService.atualizarPermissoes(this.userId,roles).subscribe(data =>{
      this.modalService.open(content,
        {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
           this.router.navigateByUrl(`/admin/users`);
         });
    },error => console.log(error))
  }

  eventSelection(event:any){
    this.form.role = (<HTMLInputElement>event).value
  }

  backClicked(){
    this.location.back();
  }

  resetPassword(s:NgForm,content:any){
    let message = new MessageDto();
    message.message = this.form.password;
    this.userService.resetarSenha(message,this.userId).subscribe(data =>{
      console.log("Senha alterada")
      this.modalService.open(content,
        {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
           this.router.navigateByUrl(`/admin/users`);
         });
    },error=>alert(error.error.message))
    s.resetForm()
  }
}
