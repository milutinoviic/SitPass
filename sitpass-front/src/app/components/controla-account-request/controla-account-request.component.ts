import { Component } from '@angular/core';
import { AccountRequest, RejectRequest } from '../../types/accountRequest.type';
import { AccountRequestService } from '../../services/accountRequest/account-request.service';

@Component({
  selector: 'app-controla-account-request',
  standalone: false,
  templateUrl: './controla-account-request.component.html',
  styleUrl: './controla-account-request.component.scss'
})
export class ControlaAccountRequestComponent {

  requests: AccountRequest[] = [];
  rejectMode: { [key: number]: boolean } = {};
  rejectReason: { [key: number]: string } = {};

  constructor(private accountRequestService: AccountRequestService) { }

  ngOnInit(): void {
    this.loadRequests();
  }

  loadRequests(): void {
    this.accountRequestService.getAllRequests().subscribe({
      next: (data) => this.requests = data,
      error: (err) => console.error(err)
    });
  }

  enableReject(id: number): void {
    this.rejectMode[id] = true;
  }

  confirmReject(request: AccountRequest): void {
    const reason = this.rejectReason[request.id];
    if (!reason || reason.trim() === '') {
      alert('Unesite razlog odbijanja.');
      return;
    }

    const dto: RejectRequest = {
      requestId: request.id,
      reason: reason
    };

    this.accountRequestService.rejectRequest(dto).subscribe({
      next: () => {
        this.rejectMode[request.id] = false;
        this.loadRequests();
      },
      error: (err) => console.error(err)
    });
  }

  approveRequest(request: AccountRequest): void {
    this.accountRequestService.approveRequest(request.id).subscribe({
      next: () => this.loadRequests(),
      error: (err) => console.error(err)
    });
  }

}
