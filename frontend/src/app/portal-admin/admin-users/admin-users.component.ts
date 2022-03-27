import { User } from './../../../model/user-dto';
import { UserService } from './../../service/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styles: [
  ]
})
export class AdminUsersComponent implements OnInit {

  public users:Array<User> = []
  constructor(private userService:UserService) { }

  ngOnInit(): void {
    this.userService.obterUsuarios().subscribe(
      data =>{
        console.log(data)
        this.users = data
      }
      ,error => console.log(error))
  }

}
