import { Post } from './../../model/post-dto';
import { User } from './../../model/user-dto';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: [
  ]
})
export class HomeComponent {

  searchText:any

  posts: Post[] = [
    {id:1, postImage: 'ford', 
    userId:1,
    user: 'carlos_marangoni'     
    ,description:'Camiseta facil de fazer'
  },
    {
      id:2, 
      postImage: 'ford', 
      userId:1,
      user:'carlos_marangoni',
      description:'Regata branca'
  },
    {
      id:3,
      postImage: 'ford', 
      userId:2,
      user: 'darlan_silva',
      description:'Mascara preta'
    },
  ];
}
