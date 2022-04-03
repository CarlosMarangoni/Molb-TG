import { TokenStorageService } from './../service/token-storage.service';
import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { MessengerService } from '../service/messenger.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styles: [
  ]
})
export class HeaderComponent implements OnInit {

  value:string = '';
  roles: string[] = [];
  info:any;
  authority: Array<string> = [];
  qty:number = 0;

  @Output() public sidenavToggle = new EventEmitter();

  constructor(private token: TokenStorageService,private msg:MessengerService) { }

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

    this.msg.getCartQty().subscribe(qtd =>
      this.qty = qtd
      )
  }
  
  onToggleSidenav = () => {
    this.sidenavToggle.emit();
  }

  logout() {
    this.token.signOut();
    window.location.href = '/login'
  }
  plusOne(){
    
  }

  isAdmin(authority:Array<string>){
    if(authority.includes('admin')){
      return true;
      console.log("is admin")
    }else{
      return false;
      console.log("is not admin")
    }
    console.log("done")
  }

}
