import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CultoffTableComponent } from './cultoff-table.component';

describe('CultoffTableComponent', () => {
  let component: CultoffTableComponent;
  let fixture: ComponentFixture<CultoffTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CultoffTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CultoffTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
