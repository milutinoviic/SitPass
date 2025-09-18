import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-facility-detail',
  standalone: false,
  templateUrl: './facility-detail.component.html',
  styleUrl: './facility-detail.component.scss'
})
export class FacilityDetailComponent {

  
  facilityId!: number;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam) {
        this.facilityId = Number(idParam);
      }
    });
  }

}
