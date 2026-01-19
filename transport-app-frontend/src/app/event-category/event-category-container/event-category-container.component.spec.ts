import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventCategoryContainerComponent } from './event-category-container.component';

describe('EventCategoryContainerComponent', () => {
  let component: EventCategoryContainerComponent;
  let fixture: ComponentFixture<EventCategoryContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EventCategoryContainerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventCategoryContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
