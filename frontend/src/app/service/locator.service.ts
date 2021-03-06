import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocatorService {

  public services: any = {};
  public urlBase: string;

  constructor() {

    this.urlBase = "/api/";

    this.services.LiberarVeiculo = this.urlBase + "veiculos/${veiculoId}/liberar";
    this.services.Posts = this.urlBase + "posts";
    this.services.Post = this.urlBase + "posts/${postId}";
    this.services.PostByUser = this.urlBase + "posts/users/${userId}";
    this.services.Comment = this.urlBase + "posts/${postId}/comment";
    this.services.Like = this.urlBase + "posts/${postId}/like";
    this.services.Login = this.urlBase + "auth/signin";
    this.services.Buy = this.urlBase + "buy"
    this.services.Purchases = this.urlBase + "purchases"
    this.services.Sales = this.urlBase + "sales"
    this.services.Contact = this.urlBase + "contact"
    this.services.Password = this.urlBase + "password"

    this.services.Register = this.urlBase + "auth/signup";
    this.services.Users = this.urlBase + "users";
    this.services.Roles = this.urlBase + "roles";
    this.services.User = this.urlBase + "users/${userId}";
    this.services.Follow = this.urlBase + "user/${userId}/follow";
  }
}