import { Post } from './post-dto';
export class PageablePostDto {
    content:Post[] = [];
    last:boolean = false;
    totalElements:number = 0;
    totalPages:number = 0;
    size:number = 0;
    number:number = 0;
    first:boolean = true;
    numberOfElements:number = 0;
    empty:boolean = true;

}