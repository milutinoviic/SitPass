import { Component, Input, OnInit } from '@angular/core';
import { Discipline, DisciplineToFromFacility } from '../../types/discipline.type';
import { DisciplineService } from '../../services/disipline/discipline.service';
import { ManagesService } from '../../services/manages/manages.service';
import { CheckManage } from '../../types/manages.type';
import { TokenService } from '../../core/utils/token.service';

@Component({
  selector: 'app-discipline-managment',
  standalone: false,
  templateUrl: './discipline-managment.component.html',
  styleUrl: './discipline-managment.component.scss'
})
export class DisciplineManagmentComponent implements OnInit {

  @Input() facilityId!: number;

  allDisciplines: Discipline[] = [];
  facilityDisciplines: Discipline[] = [];
  disciplinesToAdd: Discipline[] = [];
  disciplinesToRemove: Discipline[] = [];

  hasAccess: boolean = false;
  userId: number | null = null;

  constructor(private disciplineService: DisciplineService,private managesService:ManagesService,private tokenService:TokenService) { }

  ngOnInit() {
    this.userId = this.tokenService.getUserId();
    this.checkAccess();
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
      if (this.hasAccess) {
        this.loadData();
      } else {
        alert('You do not have access to manage this facility.');
      }
    },
    error: (err) => {
      console.error('Access check failed', err);
      this.hasAccess = false;
    }
  });
}


  loadData() {
    this.disciplineService.getAllDisciplines().subscribe(all => {
      this.allDisciplines = all;

      this.disciplineService.getDisciplinesByFacilityId(this.facilityId).subscribe(facilityList => {
        this.facilityDisciplines = facilityList;
      });
    });
  }

  // --- Helpers ---
  get assignedDisciplines(): Discipline[] {
    return this.allDisciplines.filter(d => this.isAlreadyInFacility(d));
  }

  get availableDisciplines(): Discipline[] {
    return this.allDisciplines.filter(d => !this.isAlreadyInFacility(d));
  }

  isAlreadyInFacility(d: Discipline): boolean {
    return this.facilityDisciplines.some(x => x.id === d.id);
  }

  // --- Selection ---
  selectForAdd(d: Discipline) {
    if (!this.isAlreadyInFacility(d) && !this.disciplinesToAdd.find(x => x.id === d.id)) {
      this.disciplinesToAdd.push(d);
    }
  }

  selectForRemove(d: Discipline) {
    if (this.isAlreadyInFacility(d) && !this.disciplinesToRemove.find(x => x.id === d.id)) {
      this.disciplinesToRemove.push(d);
    }
  }

  // --- Actions ---
  addDisciplines() {
    if (this.disciplinesToAdd.length === 0) return;

    const payload: DisciplineToFromFacility = {
      facilityId: this.facilityId,
      disciplineIds: this.disciplinesToAdd.map(d => d.id)
    };

    this.disciplineService.addDisciplinesToFacility(payload).subscribe({
      next: () => {
        alert('Disciplines added successfully');
        this.facilityDisciplines = [...this.facilityDisciplines, ...this.disciplinesToAdd];
        this.disciplinesToAdd = [];
      },
      error: err => alert('Error adding disciplines: ' + err.message)
    });
  }

  removeDisciplines() {
    if (this.disciplinesToRemove.length === 0) return;

    const payload: DisciplineToFromFacility = {
      facilityId: this.facilityId,
      disciplineIds: this.disciplinesToRemove.map(d => d.id)
    };

    this.disciplineService.removeDisciplinesFromFacility(payload).subscribe({
      next: () => {
        alert('Disciplines removed successfully');
        this.facilityDisciplines = this.facilityDisciplines.filter(
          d => !this.disciplinesToRemove.some(r => r.id === d.id)
        );
        this.disciplinesToRemove = [];
      },
      error: err => alert('Error removing disciplines: ' + err.message)
    });
  }
}
