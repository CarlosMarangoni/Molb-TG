import { Post } from './../../model/post-dto';
import { ActivatedRoute } from '@angular/router';
import { PostService } from './../service/post.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styles: [
  ]
})
export class PostDetailComponent implements OnInit {

  public id: number = 0;
  public post:Post = new Post();

  constructor(private postService:PostService,private route: ActivatedRoute) { 
    this.postService = postService;
  }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.postService.obterPostagem(this.id).subscribe(p => {
      this.post = p;
      console.log(p)
    },
    error => console.log(error))
  }

}
