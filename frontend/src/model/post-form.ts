import { PostItem } from './postItem-dto';
export class PostForm{
    userId: number = 0;
    title: string = "";
    description: string = "";
    categoryId:number = 0;
    items: PostItem[] = [];

    constructor(userId:number,title:string,categoryId:number,description:string,items:PostItem[]){
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.items = items;
    }

}