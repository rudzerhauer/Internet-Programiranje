import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog'
import { Event } from '../../model/event.model';
import { EventCategory } from '../../model/event.category.model';
import { EventService } from '../services/event.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EventCategoryService } from '../../event-category/services/event-category.service';

@Component({
  selector: 'app-event-edit',
  standalone: false,
  templateUrl: './event-edit.component.html',
  styleUrl: './event-edit.component.css'
})
export class EventEditComponent implements OnInit{
  public form : FormGroup = new FormGroup({});
  public event : Event = new Event();
  public categories: Array<EventCategory> = [];
  

  constructor(public formBuilder:FormBuilder, 
    private service : EventService,
    private snackBar : MatSnackBar,
    private dialogRef : MatDialogRef<EventEditComponent>,
    private categoryService : EventCategoryService) {
      this.categories = this.categoryService.getAll();
    }
  

ngOnInit(): void {
    this.form = this.formBuilder.group({
      name : [this.event.name, Validators.required],
      description : [this.event.description, Validators.required],
      image: [this.event.image],
      date : [this.event.date, Validators.required],
      time : [this.event.time, Validators.required],
      category : [this.event.category, Validators.required]

    });
}

close() {
    this.dialogRef.close();
}
save({value, valid} : { value: Event, valid: boolean}) {
      if(valid) {
        this.service.add(value);
        this.form.reset();
        this.snackBar.open("Podaci su sacuvani", undefined, {
          duration : 2000,
        });
        this.close();



      }
}

}
