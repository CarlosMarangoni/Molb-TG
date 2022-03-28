import { Role } from './../../../model/role-dto';
import { UserService } from './../../service/user.service';
import { User } from 'src/model/user-dto';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styles: [
  ]
})
export class EditUserComponent implements OnInit {

  private userId:number = 0;
  public user:User = new User();
  public roles:Role[] = []
  constructor(private route: ActivatedRoute,private userService:UserService) { }

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

}
