import { PostComments } from './comments-dto';
import { PostItem } from './postItem-dto';
export class Post{
    id:number = 0;
    postImage: string = "";
    userId: number = 0;
    stars:number = 0;
    profileImage:string = "";
    user: string = "";
    title: string = "";
    description: string = "";
    items: PostItem[] = [];
    comments: PostComments[] = [];
}