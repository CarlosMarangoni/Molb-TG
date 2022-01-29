export class PostItem {
    description:string = "";
    price:number = 0;
    
    constructor(description?:string,price?:number){
        this.description = description ?? '';
        this.price = price ?? 0;
    }
}