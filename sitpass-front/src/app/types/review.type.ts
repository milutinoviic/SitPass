import { Comment } from "./comment.type";
import { Rate } from "./rate.type";

export interface Review {
  id: number;
  createdAt: string;
  exerciseCount: number;
  hidden: boolean;
  deleted: boolean;
  userId: number;
  rate: Rate;
  comment?: Comment;
}