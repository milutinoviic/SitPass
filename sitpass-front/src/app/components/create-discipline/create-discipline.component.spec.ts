import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateDisciplineComponent } from './create-discipline.component';

describe('CreateDisciplineComponent', () => {
  let component: CreateDisciplineComponent;
  let fixture: ComponentFixture<CreateDisciplineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateDisciplineComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateDisciplineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
