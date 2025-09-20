import { User } from "./user.type";

export interface CreateManages {
  userId: number;
  facilityId: number;
}

export interface Manages {
  id: number;
  facilityId: number;
  startDate: string; 
  endDate: string;
  isDeleted: boolean;
}

export interface ManagesDetail {
  id: number;
  facilityId: number;
  startDate: string;
  endDate: string | null;
  user: User;
}

export interface CheckManage {
  userId: number;
  facilityId: number;
}