export interface Facility {
  id: number;
  name: string;
  description: string;
  address: string;
  city: string;
  totalRating: number;
  createdAt: string;
  active: boolean;
}

export interface CreateFacility {
  name: string;
  description: string;
  address: string;
  city: string;
}

export interface UpdateFacility {
  name: string;
  description: string;
  address: string;
  city: string;
}
