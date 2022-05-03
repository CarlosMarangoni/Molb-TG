import { PostItem } from "./postItem-dto";

export class PurchaseDto {
    
    id:number=0
    items:Array<PostItem> = [];
    total:number=0;
    paymentMethod:string = "";
    data:string = "";
}