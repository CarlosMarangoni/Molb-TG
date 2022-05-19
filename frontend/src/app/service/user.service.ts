import { Role } from './../../model/role-dto';
import { UserForm } from './../../model/user-form';
import { TokenStorageService } from './token-storage.service';
import { Observable } from 'rxjs';
import { LocatorService } from './locator.service';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/model/user-dto';
import { RoleSummary } from 'src/model/role-summary-dto';
import { PurchaseDto } from 'src/model/purchase-dto';
import { SaleDto } from 'src/model/sale-dto';
import { MessageDto } from 'src/model/message-dto';

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

  obterUsuarios():Observable<User[]>{
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.get<User[]>(`${this.locator.services.Users}`,{headers})
  }

  obterPermissoes(){
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.get<Role[]>(`${this.locator.services.Roles}`,{headers})
  }

  atualizarDescricao(userId:number,description:String):Observable<User>{
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.put<User>(`${this.locator.services.Users}/description/${userId}`,description,{headers})
  }

  atualizarPermissoes(userId:number,permissions:RoleSummary){
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.put(`${this.locator.services.Users}/roles/${userId}`,permissions,{headers})
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

  obterCompra(id:number){
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.get<PurchaseDto>(`${this.locator.services.Purchases}/${id}`,{headers})
  }


  obterCompras(){
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.get<PurchaseDto[]>(`${this.locator.services.Purchases}`,{headers})
  }

  obterVendas(){
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.get<SaleDto[]>(`${this.locator.services.Sales}`,{headers})
  }

  contatarTime(message:MessageDto){
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.post(`${this.locator.services.Contact}`,message,{headers})
  }

  resetarSenhaEmail(message:MessageDto){
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.post(`${this.locator.services.Password}`,message,{headers})
  }

  resetarSenha(message:MessageDto,userId:number){
    const headers =   new HttpHeaders({
      "Authorization": `Bearer ${this.token.getToken()}`
    })
    return this.http.put(`${this.locator.services.Users}/${userId}/password`,message,{headers})
  }

}
