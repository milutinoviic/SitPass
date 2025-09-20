import { Component, Input } from '@angular/core';
import { CreateManages, Manages } from '../../types/manages.type';
import { ManagesService } from '../../services/manages/manages.service';
import { User } from '../../types/user.type';
import { UserService } from '../../services/user/user.service';

@Component({
  selector: 'app-assign-manager',
  standalone: false,
  templateUrl: './assign-manager.component.html',
  styleUrl: './assign-manager.component.scss'
})
export class AssignManagerComponent {

  @Input() facilityId!: number; 
  users: User[] = [];
  result?: Manages;
  errorMessage?: string;

  constructor(
    private userService: UserService,
    private managesService: ManagesService
  ) {}

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe({
      next: (res) => (this.users = res),
      error: (err) => console.error('Greška pri učitavanju korisnika', err)
    });
  }

  assignManager(userId: number) {
    this.managesService.assignManager({ userId, facilityId: this.facilityId }).subscribe({
      next: (res) => {
        this.result = res;
        this.errorMessage = undefined;
      },
      error: (err) => {
        this.errorMessage = 'Greška prilikom dodele menadžera!';
        console.error(err);
      }
    });
  }

}
