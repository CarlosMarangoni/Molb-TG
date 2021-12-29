import { Component, OnInit } from '@angular/core';

interface Food {
  value: string;
  viewValue: string;
}

interface Post {
  postImage: string;
  user: string;
  description: string
}


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: [
  ]
})
export class HomeComponent {

  searchText:any

  foods: Food[] = [
    {value: 'steak-0', viewValue: 'Steak'},
    {value: 'pizza-1', viewValue: 'Pizza'},
    {value: 'tacos-2', viewValue: 'Tacos'},
  ];
  posts: Post[] = [
    {postImage: 'ford', user: 'carlos_marangoni',description:'Camiseta facil de fazer'},
    {postImage: 'ford', user: 'darlan_silva',description:'Regata branca'},
    {postImage: 'ford', user: 'maria_silva',description:'Mascara preta'},
  ];
  selectedFood = this.foods[2].value;


}
