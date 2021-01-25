import { TestBed } from '@angular/core/testing';

import { CulturalOfferingService } from './cultural-offering.service';

describe('CulturalOfferingService', () => {
  let service: CulturalOfferingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CulturalOfferingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
