import { Post } from './../../model/post-dto';
import { User } from './../../model/user-dto';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styles: [
  ]
})
export class ProfileComponent implements OnInit {

  users: User[] = [
    {
      id:1,
      name:'mari_costura',
      following:[
        {
          user:'darlan_silva',
          name:'Darlan Silva'
        },
        {
          user:'maria_silva',
          name:'Maria Silva'
        }
      ],
      followers:[],
      posts:[]
    },
    {
      id:2,
      name:'darlan_silva',
      following:[],
      followers:[
        {
        user:'mari_costura',
        name:'Maria Silva'
      }],
        posts:[]
    },
    {
      id:3,
      name:'maria_silva',
      following:[],
      followers:[{    
        user:'mari_costura',
        name:'Maria Silva'
       }],
        posts:[]
      }]
    
  
  
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

  public id: number = 0;
  public user:User = new User();
  public followers:number = 0;
  public following:number = 0;
  public postCount:number = 0;
  

  constructor(private route: ActivatedRoute) {
    this.route=route
   }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.user = this.users.find(u => u.id === this.id)!;
    this.followers = this.user.followers.length;
    this.following = this.user.following.length;
    
  }

  


}
