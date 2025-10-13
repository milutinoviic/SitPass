import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TokenService } from '../../core/utils/token.service';
import { CheckManage } from '../../types/manages.type';
import { ManagesService } from '../../services/manages/manages.service';
import { ExerciseService } from '../../services/exercise/exercise.service';
import { PastVisitsRequest } from '../../types/exercise.type';

@Component({
  selector: 'app-facility-detail',
  standalone: false,
  templateUrl: './facility-detail.component.html',
  styleUrl: './facility-detail.component.scss'
})
export class FacilityDetailComponent {

  facilityId!: number;
  userId: number | null = null;
  hasAccess: boolean = false;
  role: String | null = null;

  showModal = false;
  showDisciplineModal = false;
  showAccountRequestModal = false;
  showExerciseListModal = false;
  showCreateFacilityModal = false;
  showUpdateFacilityModal = false;
  showAssignManagerModal = false;
  showCreateReviewModal = false;
  canSubmitReview: boolean = false;
  showFacilityIndexModal = false;


  constructor(
    private route: ActivatedRoute,
    private tokenS: TokenService,
    private managesService: ManagesService,
    private exerciseService: ExerciseService

  ) { }

  ngOnInit(): void {
    this.userId = this.tokenS.getUserId();

    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam) {
        this.facilityId = Number(idParam);
      }
    });
    this.checkAccess()
    this.role = this.tokenS.getUserRole();

    if (this.userId && this.facilityId) {
      const request: PastVisitsRequest = {
        facilityId: this.facilityId,
        userId: this.userId
      };

      this.exerciseService.getPastVisits(request).subscribe({
        next: (visits) => {
          this.canSubmitReview = visits >= 1;
           console.log("---------MMM-------------------")
          console.log(this.canSubmitReview)
          console.log("----------------------------")
        },
        error: (err) => {
          console.error('Failed to fetch past visits', err);
          this.canSubmitReview = false;
        }
      });
    }
  }

  checkAccess() {
    const dto: CheckManage = {
      userId: this.userId || 1,
      facilityId: this.facilityId
    };
    console.log(dto)

    this.managesService.checkUserManagesFacility(dto).subscribe({
      next: (res: boolean) => {
        this.hasAccess = res;
        console.log("-----------------res--------------")
        console.log(res)
      },
      error: (err) => {
        console.error('Access check failed', err);
        this.hasAccess = false;
      }
    });
  }
}
