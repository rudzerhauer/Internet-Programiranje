import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateVoziloDialogComponent } from './createvozilodialog.component';

describe('CreatevozilodialogComponent', () => {
  let component: CreateVoziloDialogComponent;
  let fixture: ComponentFixture<CreateVoziloDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateVoziloDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(
      CreateVoziloDialogComponent
    );
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
