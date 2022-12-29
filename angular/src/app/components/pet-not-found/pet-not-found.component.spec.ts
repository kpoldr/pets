import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetNotFoundComponent } from './pet-not-found.component';

describe('PetNotFoundComponent', () => {
  let component: PetNotFoundComponent;
  let fixture: ComponentFixture<PetNotFoundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PetNotFoundComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PetNotFoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
