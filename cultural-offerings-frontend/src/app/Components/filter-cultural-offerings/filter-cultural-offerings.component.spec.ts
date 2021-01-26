import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterCulturalOfferingsComponent } from './filter-cultural-offerings.component';

describe('FilterCulturalOfferingsComponent', () => {
  let component: FilterCulturalOfferingsComponent;
  let fixture: ComponentFixture<FilterCulturalOfferingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilterCulturalOfferingsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FilterCulturalOfferingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
