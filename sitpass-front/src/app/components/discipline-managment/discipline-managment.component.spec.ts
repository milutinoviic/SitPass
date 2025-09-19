import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisciplineManagmentComponent } from './discipline-managment.component';

describe('DisciplineManagmentComponent', () => {
  let component: DisciplineManagmentComponent;
  let fixture: ComponentFixture<DisciplineManagmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DisciplineManagmentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisciplineManagmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
