import { PostForm } from './../../model/post-form';
import { PageablePostDto } from './../../model/pageable-post-dto';
import { TokenStorageService } from './../service/token-storage.service';
import { LocatorService } from './locator.service';
import { Post } from './../../model/post-dto';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
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

  cadastrarPost(postForm:PostForm){
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.post<PostForm>(`${this.locator.services.Posts}`,postForm,{headers})
  }

  cadastrarPostComArquivo(postForm:PostForm,file:File){
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    const formData = new FormData();
    const data = new Blob([JSON.stringify({
      userId:postForm.userId,
      description:postForm.description,
      title:postForm.title,
      items:postForm.items
    })],{
      type: 'application/json'
    })
    formData.append('file',file,file.name)
    formData.append('post',data)

    console.log(JSON.stringify({postForm}));

    const request = new HttpRequest('POST',this.locator.services.Posts, formData,{headers})

    return this.http.request(request);

  }
}
