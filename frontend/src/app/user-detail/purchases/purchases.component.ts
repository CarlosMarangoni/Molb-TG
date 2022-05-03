import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { PurchaseDto } from 'src/model/purchase-dto';

@Component({
  selector: 'app-purchases',
  templateUrl: './purchases.component.html',
  styles: [
  ]
})
export class PurchasesComponent implements OnInit {

  purchases:PurchaseDto[] = [];

  constructor(private userService:UserService) { }

  ngOnInit(): void {
    this.userService.obterCompras().subscribe(data =>{
      console.log(data)
      this.purchases = data;

    },error => console.log(error))
  }

}
