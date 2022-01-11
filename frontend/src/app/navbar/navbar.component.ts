import { TokenStorageService } from './../service/token-storage.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent {
  value:string = '';
  info:any;

  constructor(private token: TokenStorageService){

  }

  ngOnInit(): void {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      profilePic: this.token.getProfilePic(),
      authorities: this.token.getAuthorities()
    };
  }

  logout() {
    this.token.signOut();
    window.location.href = '/login'
  }
}
