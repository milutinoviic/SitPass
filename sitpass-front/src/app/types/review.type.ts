import { Comment, CreateComment } from "./comment.type";
import { CreateRate, Rate } from "./rate.type";

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

export interface CreateReview {
  rate: CreateRate;
  comment?: CreateComment;
  userId: number;
}