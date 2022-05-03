import { Component, OnInit } from '@angular/core';
import { User } from 'src/model/user-dto';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styles: [
  ]
})
export class ForgotPasswordComponent implements OnInit {

  public emailExists:boolean = false;
  public user:User=new User();

  constructor() { }

  ngOnInit(): void {
  }

  recoverPassword(email:string){
    console.log(email)
  }

}
