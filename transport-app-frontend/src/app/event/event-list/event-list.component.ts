import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table'
import { EventService } from '../services/event.service';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmModalComponent } from '../../confirm-modal/confirm-modal.component';
import { EventEditComponent } from '../event-edit/event-edit.component';
import { Event } from '../../model/event.model';

@Component({
  selector: 'app-event-list',
  standalone: false,
  templateUrl: './event-list.component.html',
  styleUrl: './event-list.component.css'
})
export class EventListComponent implements OnInit {


    displayedColumns: string [] = ['name', 'description', 'image', 'category', 'date', 'time', 'delete'];
    dataSource = new MatTableDataSource<Event>();


    constructor(private service: EventService, private dialog: MatDialog) {}
    


  ngOnInit() {
      this.dataSource.data = this.service.getAll();
  }


  add() {
    this.dialog.open(EventEditComponent, {

      width: '600px'
    })
    .afterClosed()
    .subscribe(result => {
        this.dataSource.data = this.service.getAll();
    });
  }
  delete(element : any) {
    this.dialog.open(ConfirmModalComponent, {
      width:'300px'
    })

      .afterClosed()
      .subscribe(result => {
        if(result) {
          this.service.delete(element.id);
          this.dataSource.data = this.service.getAll();
        }

      })


  }
}
