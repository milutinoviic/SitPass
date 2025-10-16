import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityImagesComponent } from './facility-images.component';

describe('FacilityImagesComponent', () => {
  let component: FacilityImagesComponent;
  let fixture: ComponentFixture<FacilityImagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FacilityImagesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacilityImagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
