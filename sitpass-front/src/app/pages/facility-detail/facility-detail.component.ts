import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TokenService } from '../../core/utils/token.service';

@Component({
  selector: 'app-facility-detail',
  standalone: false,
  templateUrl: './facility-detail.component.html',
  styleUrl: './facility-detail.component.scss'
})
export class FacilityDetailComponent {

  
  facilityId!: number;
  userId: number | null = null;

  constructor(private route: ActivatedRoute,private tokenS:TokenService) {}

  ngOnInit(): void {
    this.userId = this.tokenS.getUserId();
    console.log("-------------T-----------------")
    console.log(this.userId)
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam) {
        this.facilityId = Number(idParam);
      }
    });
  }

}
