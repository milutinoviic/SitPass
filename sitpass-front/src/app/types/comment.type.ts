export interface Comment {
  id: number;
  text: string;
  createdAt: string;
  userId: number;
  repliesToId?: number;
}