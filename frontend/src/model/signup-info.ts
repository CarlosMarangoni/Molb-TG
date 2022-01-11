export class SignUpInfo{
    username:string = "";
    name:string = "";
    description:string = "";
    email:string = "";
    password:string = "";
    roles:string[] = [];
 
    constructor(username:string,name:string,description:string,email:string,password:string){
        this.username = username;
        this.name = name;
        this.description = description;
        this.email = email;
        this.password = password;
        this.roles = ['user']
    }
}