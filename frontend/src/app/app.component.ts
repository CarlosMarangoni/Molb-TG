import { TokenStorageService } from './service/token-storage.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl:  './app.component.html',
  styles: []
})
export class AppComponent implements OnInit {
  title = 'Molsew';
  roles: string[] = [];
  authority: string = "";
  
  constructor(private tokenStorage: TokenStorageService) { }
  
  
  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
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
  

}

