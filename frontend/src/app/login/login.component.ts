import { LoginService } from './../service/login.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: [
  ]
})
export class LoginComponent implements OnInit {
  hide = true;

  username!: string;
  password!:string;
  message:any

  constructor(private service:LoginService,private router:Router) { }

  ngOnInit(): void {
  }


  doLogin(){
    let resp = this.service.login(this.username,this.password);
    resp.subscribe(data=>{
      this.message=data;
      this.router.navigate(["/home"])
    })
  }

}
