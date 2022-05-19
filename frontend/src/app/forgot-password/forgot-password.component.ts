import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageDto } from 'src/model/message-dto';
import { User } from 'src/model/user-dto';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styles: [
  ]
})
export class ForgotPasswordComponent implements OnInit {

  public form:any = {};
  public email:string = "";
  public message:string = ""

  constructor(private modalService: NgbModal,private userService: UserService) { }

  ngOnInit(): void {
  }

  onSubmit(f: NgForm,content:any){
    this.email = this.form.message
    let email = new MessageDto();
    email.message = this.email
    this.userService.resetarSenhaEmail(email).subscribe(data =>{
      console.log(data)
      this.message = ""
      this.modalService.open(content,
        {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
           
         });
    },error=>this.message = error.error.message);
    
       f.resetForm()
  }

  hasError(){
    if(this.message.length > 0){
      return true
    }
    return false;
  }

}
