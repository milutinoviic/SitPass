export interface Rate {
  id: number;
  equipment: number;
  staff: number;
  hygiene: number;
  space: number;
}

export interface CreateRate {
  equipment: number;
  staff: number;
  hygiene: number;
  space: number;
}