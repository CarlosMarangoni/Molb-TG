import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { SaleDto } from 'src/model/sale-dto';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styles: [
  ]
})
export class SalesComponent implements OnInit {

  id:number = 0;
  sales:SaleDto[] = [];


  constructor(private userService:UserService,private router:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = Number(this.router.snapshot.paramMap.get('id'));
    console.log(this.id)
    this.userService.obterVendas().subscribe(data =>{
      console.log(data);
      this.sales = data;
    },error => console.log(error))
  }

}
