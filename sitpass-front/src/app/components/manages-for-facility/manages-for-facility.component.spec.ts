import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagesForFacilityComponent } from './manages-for-facility.component';

describe('ManagesForFacilityComponent', () => {
  let component: ManagesForFacilityComponent;
  let fixture: ComponentFixture<ManagesForFacilityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ManagesForFacilityComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManagesForFacilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
