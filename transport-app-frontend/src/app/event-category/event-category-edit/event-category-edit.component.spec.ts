import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventCategoryEditComponent } from './event-category-edit.component';

describe('EventCategoryEditComponent', () => {
  let component: EventCategoryEditComponent;
  let fixture: ComponentFixture<EventCategoryEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EventCategoryEditComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventCategoryEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
