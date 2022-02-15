import { UserForm } from './../../model/user-form';
import { TokenStorageService } from './token-storage.service';
import { Observable } from 'rxjs';
import { LocatorService } from './locator.service';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
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

  cadastrarUsuario(userForm:UserForm){
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    });
    
    const formData = new FormData();
    const data = new Blob([JSON.stringify({
      email:userForm.email,
      name:userForm.name,
      description:userForm.description,
      username:userForm.username,
      password:userForm.password,
      roles:userForm.roles
    })],{
      type: 'application/json'
    })
    formData.append('user',data)

    const request = new HttpRequest('POST',this.locator.services.Register, formData,{headers})

    return this.http.request(request);
  }

  cadastrarUsuarioComArquivo(userForm:UserForm,file:File){
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    const formData = new FormData();
    const data = new Blob([JSON.stringify({
      email:userForm.email,
      name:userForm.name,
      description:userForm.description,
      username:userForm.username,
      password:userForm.password,
      roles:userForm.roles
    })],{
      type: 'application/json'
    })
    formData.append('file',file,file.name)
    formData.append('user',data)

    const request = new HttpRequest('POST',this.locator.services.Register, formData,{headers})

    return this.http.request(request);

  }

}
