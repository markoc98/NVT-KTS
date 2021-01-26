import { TestBed } from '@angular/core/testing';

import { NewsletterServiceService } from './newsletter-service.service';

describe('NewsletterServiceService', () => {
  let service: NewsletterServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NewsletterServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
