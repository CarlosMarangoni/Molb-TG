import { LoginComponent } from './../login/login.component';
import { TokenStorageService } from './../service/token-storage.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent {
  value:string = '';
  roles: string[] = [];
  info:any;
  authority: string = "";

  constructor(private token: TokenStorageService){

  }

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
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        } else if (role === 'ROLE_CREATOR') {
          this.authority = 'creator';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
  }

  logout() {
    this.token.signOut();
    window.location.href = '/login'
  }
}
