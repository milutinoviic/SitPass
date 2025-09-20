export interface Comment {
  id: number;
  text: string;
  createdAt: string;
  userId: number;
  repliesToId?: number;
}

export interface CreateComment {
  text: string;
  repliesToId?: number | null;
}
