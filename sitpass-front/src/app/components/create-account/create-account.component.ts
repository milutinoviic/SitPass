import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AccountRequest, CreateAccountRequest } from '../../types/accountRequest.type';
import { AccountRequestService } from '../../services/accountRequest/account-request.service';

@Component({
  selector: 'app-create-account',
  standalone: false,
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.scss'
})
export class CreateAccountComponent {

  accountForm!: FormGroup;  
  response?: AccountRequest;
  errorMessage?: string;

  constructor(
    private fb: FormBuilder,
    private accountService: AccountRequestService
  ) { }

  ngOnInit(): void {
    this.accountForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      address: ['', [Validators.required, Validators.minLength(3)]]
    });
  }

  onSubmit(): void {
    if (this.accountForm.valid) {
      const dto: CreateAccountRequest = this.accountForm.value as CreateAccountRequest;
      this.accountService.createAccountRequest(dto).subscribe({
        next: (res) => {
          this.response = res;
          this.errorMessage = undefined;
        },
        error: (err) => {
          this.errorMessage = 'Greška pri kreiranju naloga';
          console.error(err);
        }
      });
    }
  }

}
