import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BackwardDemonstracija } from './backward-demonstracija';

describe('BackwardDemonstracija', () => {
  let component: BackwardDemonstracija;
  let fixture: ComponentFixture<BackwardDemonstracija>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BackwardDemonstracija],
    }).compileComponents();

    fixture = TestBed.createComponent(BackwardDemonstracija);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
