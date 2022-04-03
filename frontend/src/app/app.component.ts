import { LoginComponent } from './login/login.component';
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
  authority: string[] = [];
  mostrarMenu:boolean = false;

  constructor(private tokenStorage: TokenStorageService) { }
  
  
  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
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
  

}

