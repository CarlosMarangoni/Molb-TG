import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent {
  items!: MenuItem[];


  ngOnInit(): void {
    this.items = [
      {
        icon: 'pi pi-fw pi-search',        
      },
      {
       
        icon: 'pi pi-fw pi-user',
        items: [
          {
            label: 'Logout',
            icon: 'pi pi-fw pi-power-off',
            
          },
          {
            label: 'Profile',
            icon: 'pi pi-fw pi-users'           
          }
        ]
      }
    ];
  }

}
