import { PageablePostDto } from './../../model/pageable-post-dto';
import { LocatorService } from './locator.service';
import { Post } from './../../model/post-dto';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http:HttpClient,private locator: LocatorService) {

   }

   obterTodasPostagens() : Observable<PageablePostDto>{
    return this.http.get<PageablePostDto>(this.locator.services.Posts)     
   }

   obterTodasPostagensPaginadas(activePage:number) : Observable<PageablePostDto>{
    return this.http.get<PageablePostDto>(`${this.locator.services.Posts}?page=${activePage}&size=9`)     
   }
}
