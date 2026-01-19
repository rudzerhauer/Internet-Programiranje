import { TestBed } from '@angular/core/testing';

import { VoziloService } from './vozilo.service';

describe('VoziloService', () => {
  let service: VoziloService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VoziloService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
