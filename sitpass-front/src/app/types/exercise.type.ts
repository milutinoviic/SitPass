export interface Exercise {
  id: number;
  facilityId: number;
  userId: number;
  from: string;   
  until: string;
}

export interface CreateExercise {
  userId: number;
  facilityId: number;
  from: string;   
  until: string;
}

export interface PastVisitsRequest {
  facilityId: number;
  userId: number;
}
