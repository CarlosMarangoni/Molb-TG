import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styles: [
  ]
})
export class NewPostComponent implements OnInit {

  public uploaded:boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

}
