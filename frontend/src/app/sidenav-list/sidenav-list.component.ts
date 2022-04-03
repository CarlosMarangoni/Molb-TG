import { TokenStorageService } from './../service/token-storage.service';
import { Component, OnInit,EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-sidenav-list',
  templateUrl: './sidenav-list.component.html',
  styles: [
  ]
})
export class SidenavListComponent implements OnInit {

  value:string = '';
  roles: string[] = [];
  info:any;
  authority: string[] = [];

  @Output() sidenavClose = new EventEmitter();

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

  public onSidenavClose = () => {
    this.sidenavClose.emit();
  }

  logout() {
    this.sidenavClose.emit();
    this.token.signOut();
    window.location.href = '/login'
  }

}
