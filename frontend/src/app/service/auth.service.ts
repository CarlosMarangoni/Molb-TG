import { SignUpInfo } from './../../model/signup-info';
import { Observable } from 'rxjs';
import { JwtResponse } from './../../model/jwt-response';
import { AuthLoginInfo } from './../../model/login-info';
import { LocatorService } from './locator.service';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'
})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private locator:LocatorService,private http: HttpClient) { }
  
  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.locator.services.Login, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.locator.services.Register, info, httpOptions);
  }
}
