import { FollowerDto } from './follower-dto';
import { FollowingDto } from './following-dto';
import { PostSummaryDto } from "./post-summary-dto";

export class User {
    id:number = 0;
    user:string = "";
    username:string = "";
    profileImage:string = "";
    description:string = "";
    name:string = "";
    permissions:string[] = [];
    following:FollowingDto[] = [];
    followers:FollowerDto[] = [];
    posts:PostSummaryDto[] = [];
}