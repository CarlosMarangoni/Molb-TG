import { LocatorService } from './locator.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient,private locator: LocatorService) { }

  public login(username:string,password:string){
    const headers = new HttpHeaders({Authorization: 'Basic ' + btoa(username+":"+password)})
    return this.http.get(this.locator.services.Login,{headers,responseType:'text' as 'json'})
  }

}
