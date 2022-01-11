import { User } from './user-dto';
export class JwtResponse{
    token:string = "";
    type:string = "";
    authorities: string[] = [];
    user:User = new User();

}