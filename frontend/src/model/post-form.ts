import { PostItem } from './postItem-dto';
export class PostForm{
    userId: number = 0;
    title: string = "";
    description: string = "";
    category:string = "";
    items: PostItem[] = [];

    constructor(userId:number,title:string,category:string,description:string,items:PostItem[]){
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.items = items;
    }

}