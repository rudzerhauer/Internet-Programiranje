import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoziloManagementComponent } from './vozilo-managment.component';

describe('VoziloManagementComponent', () => {
  let component: VoziloManagementComponent;
  let fixture: ComponentFixture<VoziloManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VoziloManagementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VoziloManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
