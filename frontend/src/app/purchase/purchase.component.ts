import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PurchaseDto } from 'src/model/purchase-dto';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styles: [
  ]
})
export class PurchaseComponent implements OnInit {

  id:number = 0;
  purchase:PurchaseDto = new PurchaseDto();

  constructor(private userService:UserService,private router:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = Number(this.router.snapshot.paramMap.get('id'));
    console.log(this.id)
    this.userService.obterCompra(this.id).subscribe(data =>{
      console.log(data);
      this.purchase = data;
    },error => console.log(error))
  }

}
