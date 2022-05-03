import { Component, OnInit, Output } from '@angular/core';
import { TokenStorageService } from '../service/token-storage.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styles: [
  ]
})
export class UserDetailComponent implements OnInit {

  public authority:Array<string> = [];
 
  roles: string[] = [];

  @Output() info:any;  

  constructor(private token: TokenStorageService) { }

  ngOnInit(): void {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      profilePic: this.token.getProfilePic(),
      authorities: this.token.getAuthorities(),
      id: this.token.getUserId()
    }
    if (this.token.getToken()) {
      this.roles = this.token.getAuthorities();
      this.roles.forEach(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority.push('admin');
        } else if (role === 'ROLE_CREATOR') {
          this.authority.push('creator');
        }
        this.authority.push('user');;
      });
    }
  }


  isUser(authority:Array<string>){
    if(authority.includes('user')){
      return true;
    }else{
      return false;
    }
  }

  isCreator(authority:Array<string>){
    if(authority.includes('creator')){
      return true;
    }else{
      return false;
    }
  }
}
