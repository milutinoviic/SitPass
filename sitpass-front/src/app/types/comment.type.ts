export interface Comment {
  id: number;
  text: string;
  createdAt: string;
  userId: number;
  repliesToId?: number;
  replies?: Comment[]; 
}


export interface CreateComment {
  text: string;
  repliesToId?: number | null;
}

export interface CommentReply {
  commentId: number;
  userId: number;
  content: string;
}

