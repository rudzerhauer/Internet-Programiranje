import { Injectable } from "@angular/core";
import { EventCategory } from "../../model/event.category.model";
import { EventService } from "../../event/services/event.service";

@Injectable( {
    providedIn: 'root'
})

export class EventCategoryService {
    private STORAGE_KEY: string ="event-category";
    public data : Array<EventCategory> = [];

    constructor(private eventService: EventService) {
        this.data.push(new EventCategory(1, "krucina"));
        this.data.push(new EventCategory(2, "drugakrucina"));
        this.data.push(new EventCategory(3, "trecakurcina"));
    }

    public saveToLocalStorage() {
        localStorage.setItem(this.STORAGE_KEY, JSON.stringify(this.data));
    }
    public loadFromLocalStorage() {
        let data = localStorage.getItem(this.STORAGE_KEY) || "[]";
        this.data = JSON.parse(data) || [];
    }

    public add(eventCategory : EventCategory) {
        eventCategory.id = new Date().getTime();
        this.data.push(eventCategory);
        this.saveToLocalStorage();

    }



    public getAll()  {
        this.loadFromLocalStorage();
        return this.data;
    }

    public edit(id : number | null, eventCategory : EventCategory) {
        this.data.forEach(item => {
            if(item.id == id) {
                Object.assign(item, eventCategory);
                this.saveToLocalStorage();
                this.updateEvents(item);
            }
        })
    }

    private updateEvents(newCategory : any) {
        let events = this.eventService.getAll();
        events.forEach((event : any) => {
            if(event.category.id == newCategory.id) {
                event.category = newCategory;
            }
        });
        this.eventService.data = events;
        this.eventService.saveToLocalStorage();
    }

    

    
}
