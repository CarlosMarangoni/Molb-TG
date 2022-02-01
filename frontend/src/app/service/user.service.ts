import { TokenStorageService } from './token-storage.service';
import { Observable } from 'rxjs';
import { LocatorService } from './locator.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/model/user-dto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient,private locator: LocatorService,private token:TokenStorageService) { }

  obterUsuario(userId:number):Observable<User>{
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.get<User>(`${this.locator.services.Users}/${userId}`,{headers})
  }

  atualizarDescricao(userId:number,description:String):Observable<User>{
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.put<User>(`${this.locator.services.Users}/${userId}`,description,{headers})
  }

  seguirUsuario(userId:number):Observable<User>{
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.post<User>(`${this.locator.services.Users}/${userId}/follow`,'',{headers})
  }

}
