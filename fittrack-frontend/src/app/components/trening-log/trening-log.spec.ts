import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TreningLog } from './trening-log';

describe('TreningLog', () => {
  let component: TreningLog;
  let fixture: ComponentFixture<TreningLog>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TreningLog],
    }).compileComponents();

    fixture = TestBed.createComponent(TreningLog);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
