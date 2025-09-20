import { Component, Input } from '@angular/core';
import { CreateManages, ManagesDetail } from '../../types/manages.type';
import { ManagesService } from '../../services/manages/manages.service';

@Component({
  selector: 'app-manages-for-facility',
  standalone: false,
  templateUrl: './manages-for-facility.component.html',
  styleUrl: './manages-for-facility.component.scss'
})
export class ManagesForFacilityComponent {

  @Input() facilityId!: number;
  manages: ManagesDetail[] = [];

  constructor(private managesService: ManagesService) {}

  ngOnInit(): void {
    if (this.facilityId) {
      this.loadManagers();
    }
  }

  loadManagers() {
    this.managesService.getAllManagesForFacility(this.facilityId).subscribe({
      next: (data) => this.manages = data,
      error: (err) => console.error('Error fetching managers', err)
    });
  }

  izbaciManager(manager: ManagesDetail) {
    const dto: CreateManages = {
      userId: manager.user.id,
      facilityId: manager.facilityId
    };

    this.managesService.deleteManager(dto).subscribe({
      next: () => {
        console.log('Izbrisan menadžer ID:', manager.id);
        // ukloni iz prikaza
        this.manages = this.manages.filter(m => m.id !== manager.id);
      },
      error: (err) => console.error('Greška pri brisanju menadžera', err)
    });
  }

}
