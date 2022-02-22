import { TokenStorageService } from './../service/token-storage.service';
import { Post } from './../../model/post-dto';
import { PostService } from './../service/post.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styles: [
  ]
})
export class BuyComponent implements OnInit {

  public post:Post = new Post();
  public id:number = 0;
  public owner:boolean = false;

  constructor(private route: ActivatedRoute,private postService:PostService,private token:TokenStorageService) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.postService.obterPostagem(this.id).subscribe(p => {
      this.post = p;
      if (p.userId === Number(this.token.getUserId())){
        this.owner = true; 
        
      }
      console.log(p)
    },
    error => console.log(error));
  }

}
