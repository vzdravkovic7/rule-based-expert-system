import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CepSimulacija } from './cep-simulacija';

describe('CepSimulacija', () => {
  let component: CepSimulacija;
  let fixture: ComponentFixture<CepSimulacija>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CepSimulacija],
    }).compileComponents();

    fixture = TestBed.createComponent(CepSimulacija);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
