import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilForma } from './profil-forma';

describe('ProfilForma', () => {
  let component: ProfilForma;
  let fixture: ComponentFixture<ProfilForma>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfilForma],
    }).compileComponents();

    fixture = TestBed.createComponent(ProfilForma);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
