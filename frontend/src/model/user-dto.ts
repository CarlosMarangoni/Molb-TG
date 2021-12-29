import { FollowerDto } from './follower-dto';
import { FollowingDto } from './following-dto';
import { PostSummaryDto } from "./post-summary-dto";

export class User {
    id:number = 0;
    name:string = "";
    following:FollowingDto[] = [];
    followers:FollowerDto[] = [];
    posts:PostSummaryDto[] = [];
}