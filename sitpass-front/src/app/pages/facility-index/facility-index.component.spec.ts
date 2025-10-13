import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityIndexComponent } from './facility-index.component';

describe('FacilityIndexComponent', () => {
  let component: FacilityIndexComponent;
  let fixture: ComponentFixture<FacilityIndexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FacilityIndexComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacilityIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
