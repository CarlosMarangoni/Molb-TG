export class CommentForm {
    description:string = "";
    stars:number = 0;

    constructor(description:string,stars:number){
        this.description = description;
        this.stars = stars;
    }
}