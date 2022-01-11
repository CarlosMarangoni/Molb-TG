import { PageablePostDto } from './../../model/pageable-post-dto';
import { TokenStorageService } from './../service/token-storage.service';
import { LocatorService } from './locator.service';
import { Post } from './../../model/post-dto';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http:HttpClient,private locator: LocatorService,private token: TokenStorageService) {
    
   }

   obterPostagem(postId:number) : Observable<Post>{
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.get<Post>(`${this.locator.services.Posts}/${postId}`,{headers})
   }


   obterTodasPostagens() : Observable<PageablePostDto>{
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.get<PageablePostDto>(this.locator.services.Posts,{headers})     
   }

   obterTodasPostagensPaginadas(activePage:number) : Observable<PageablePostDto>{
    const headers =   new HttpHeaders({
    "Authorization": `Bearer ${this.token.getToken()}`
  })
    return this.http.get<PageablePostDto>(`${this.locator.services.Posts}?page=${activePage}&size=9`,{headers})     
   }

   obterPostsdeUsuario(userId:number):Observable<Post[]>{
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
      return this.http.get<Post[]>(`${this.locator.services.Posts}/user/${userId}`,{headers})
  }
}
