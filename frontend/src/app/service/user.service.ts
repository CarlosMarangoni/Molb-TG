import { Observable } from 'rxjs';
import { LocatorService } from './locator.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/model/user-dto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient,private locator: LocatorService) { }

  obterUsuario(userId:number):Observable<User>{
    return this.http.get<User>(`${this.locator.services.Users}/${userId}`)
  }

}
