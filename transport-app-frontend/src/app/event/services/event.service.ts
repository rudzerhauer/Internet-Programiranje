import { Injectable, PLATFORM_ID, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Event } from '../../model/event.model';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private readonly STORAGE_KEY: string = "events";
  public data: Array<Event> = [];

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  public saveToLocalStorage(): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem(this.STORAGE_KEY, JSON.stringify(this.data));
    }
  }

  public loadFromLocalStorage(): void {
    if (isPlatformBrowser(this.platformId)) {
      const data = localStorage.getItem(this.STORAGE_KEY) || "[]";
      this.data = JSON.parse(data) || [];
      this.data.forEach((e: any) => {
        e.date = new Date(e.date);
      });
    } else {
      this.data = [];
    }
  }

  public add(event: Event): void {
    event.id = new Date().getTime();
    this.data.push(event);
    this.saveToLocalStorage();
  }

  public delete(id: number): void {
    const position = this.data.findIndex(item => item.id === id);
    if (position > -1) {
      this.data.splice(position, 1);
      this.saveToLocalStorage();
    }
  }

  public getAll(): Array<Event> {
    this.loadFromLocalStorage();
    return this.data;
  }

  public getTodaysEvents(): Array<Event> {
    const today = new Date();
    return this.getAll().filter((e: Event) => {
      const eDate = e.date;
      if (!eDate) return false; // Handle null or undefined
      return eDate.getDate() === today.getDate() &&
             eDate.getMonth() === today.getMonth() &&
             eDate.getFullYear() === today.getFullYear();
    });
  }

  public getFutureEvents(): Array<Event> {
    const now = new Date().getTime();
    return this.getAll().filter((e: Event) => e.date && e.date.getTime() >= now);
  }
}