export interface CreateAccountRequest {
  email: string;
  password: string;
  address: string;
}

export interface AccountRequest {
  id: number;
  email: string;
  address: string;
  status: string;
  rejectionReason: string;
  createdAt: string;
}

export interface RejectRequest {
  requestId: number;
  reason: string;
}