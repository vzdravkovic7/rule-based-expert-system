import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Nutricija } from './nutricija';

describe('Nutricija', () => {
  let component: Nutricija;
  let fixture: ComponentFixture<Nutricija>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Nutricija],
    }).compileComponents();

    fixture = TestBed.createComponent(Nutricija);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
