import { Component, OnInit } from '@angular/core';
import { EventCategory } from '../../model/event.category.model';
import { Event } from '../../model/event.model';
import { EventService } from '../../event/services/event.service';
import { EventCategoryService } from '../../event-category/services/event-category.service';


@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  public eventsToday: Array<Event> = [];
  public events: Array<Event> = []; //svi za filter
  public categories: Array<EventCategory> = [];


  constructor(private service:EventService, private categoryService:EventCategoryService){
    this.events=this.service.getFutureEvents();
    this.eventsToday=this.service.getTodaysEvents();
    this.categories = this.categoryService.getAll();
  }

  ngOnInit(): void {
      
  }


  filterCategory(event : any) {
    let category = event.value.id;
    this.events = this.service.getFutureEvents().filter((e: any) => {
      return e.category.id == category
    });
  }
}
