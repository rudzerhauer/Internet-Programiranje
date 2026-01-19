import { Component, OnInit } from '@angular/core';
import { EventCategory } from '../../model/event.category.model';
import { MatTableDataSource } from '@angular/material/table'
import { EventCategoryService } from '../services/event-category.service';
import { MatDialog } from '@angular/material/dialog';
import { EventCategoryEditComponent } from '../event-category-edit/event-category-edit.component';
@Component({
  selector: 'app-event-category-list',
  standalone: false,
  templateUrl: './event-category-list.component.html',
  styleUrl: './event-category-list.component.css'
})
export class EventCategoryListComponent implements OnInit{
 
    displayedColumns : string[] =['name', 'edit'];
    dataSource = new MatTableDataSource<EventCategory>();


    constructor(private service: EventCategoryService,
      private dialog: MatDialog) {

      }
    
  ngOnInit(){
      this.dataSource.data = this.service.getAll();
  }

  add() {
      this.dialog.open(EventCategoryEditComponent, {
        width:'600px',
        data : {
          EventCategory: new EventCategory(),
          isEdit: false
        }
      })
      .afterClosed()
      .subscribe(result => {
        this.dataSource.data = this.service.getAll();
      })


  }


  edit(element : any) {
    this.dialog.open(EventCategoryEditComponent, {
      width:"600px",
      data: {
        eventCategory : element,
        isEdit: true
      }
    })
    .afterClosed()
    .subscribe(result => {
      this.dataSource.data = this.service.getAll();
    })


  }
}
