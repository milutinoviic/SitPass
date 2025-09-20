import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ControlaAccountRequestComponent } from './controla-account-request.component';

describe('ControlaAccountRequestComponent', () => {
  let component: ControlaAccountRequestComponent;
  let fixture: ComponentFixture<ControlaAccountRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ControlaAccountRequestComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ControlaAccountRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
