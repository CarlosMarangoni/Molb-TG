import { User } from './../../model/user-dto';
import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthToken';
const USERNAME_KEY = 'AuthUsername';
const PROFILEPIC_KEY = 'AuthUserProfilePic'
const USERID_KEY = 'AuthUserId'
const AUTHORITIES_KEY = 'AuthAuthorities';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  

  private roles: Array<string> = [];
  constructor() { }

  signOut() {
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }
  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY)!;
  }

  public saveUsername(username: User) {
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.removeItem(PROFILEPIC_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, username.user);
    window.sessionStorage.setItem(PROFILEPIC_KEY, username.profileImage);
    window.sessionStorage.setItem(USERID_KEY, username.id.toString());
    window.sessionStorage.setItem(USERID_KEY, username.id.toString());
  }

  public getUsername(): string {
    return sessionStorage.getItem(USERNAME_KEY)!;
  }

  public getProfilePic(): string {
    return sessionStorage.getItem(PROFILEPIC_KEY)!;
  }
  public getUserId(): string {
    return sessionStorage.getItem(USERID_KEY)!;
  }


  public saveAuthorities(authorities: string[]) {
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  public getAuthorities(): string[] {
    this.roles = [];

    if (sessionStorage.getItem(TOKEN_KEY)) {
      JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)!).forEach((authority: { authority: string; }) => {
        this.roles.push(authority.authority);
      });
    }

    return this.roles;
  }


}
