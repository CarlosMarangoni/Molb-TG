import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocatorService {

  public services: any = {};
  public urlBase: string;

  constructor() {

    this.urlBase = "http://localhost:8080/";

    this.services.LiberarVeiculo = this.urlBase + "veiculos/${veiculoId}/liberar";
    this.services.Posts = this.urlBase + "posts";
    this.services.Post = this.urlBase + "posts/${postId}";
    this.services.Comment = this.urlBase + "posts/${postId}/comment";
    this.services.Like = this.urlBase + "posts/${postId}/like";
    this.services.Login = this.urlBase + "login";

    this.services.Register = this.urlBase + "register";
    this.services.Users = this.urlBase + "users";
    this.services.User = this.urlBase + "user/${userId}";
    this.services.Follow = this.urlBase + "user/${userId}/follow";

    this.services.Romaneio = this.urlBase + "romaneios/";
    this.services.ObterCoordenadas = this.urlBase + "romaneios/coordenadas/";

  }
}