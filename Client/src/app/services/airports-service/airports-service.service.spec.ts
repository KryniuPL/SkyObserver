import { TestBed, inject } from '@angular/core/testing';

import { AirportsServiceService } from './airports-service.service';

describe('AirportsServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AirportsServiceService]
    });
  });

  it('should be created', inject([AirportsServiceService], (service: AirportsServiceService) => {
    expect(service).toBeTruthy();
  }));
});
