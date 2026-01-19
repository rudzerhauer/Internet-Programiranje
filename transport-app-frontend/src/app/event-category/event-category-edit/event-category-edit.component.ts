import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog'
import { EventCategory } from '../../model/event.category.model';
import { EventCategoryService } from '../services/event-category.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-event-category-edit',
  standalone: false,
  templateUrl: './event-category-edit.component.html',
  styleUrl: './event-category-edit.component.css'
})
export class EventCategoryEditComponent implements OnInit {
    public form: FormGroup = new FormGroup({});
    public eventCategory : EventCategory = new EventCategory();
    private isEdit = false;


    constructor(public formBuilder:FormBuilder,
      private service:EventCategoryService,
      private snackBar : MatSnackBar,
      private dialogRef : MatDialogRef<EventCategoryEditComponent>,
      @Inject(MAT_DIALOG_DATA) data:any) {
        this.isEdit = data.isEdit;
        this.eventCategory = data.eventCategory;
      }




  ngOnInit(){
      this.form = this.formBuilder.group({
        name : [this.eventCategory.name, Validators.required]
      });
  }

  save({ value, valid} : {value: EventCategory, valid: boolean}) {
    if(valid) {
      if(this.isEdit) {
        this.service.edit(this.eventCategory.id, value);
      } else {
        this.service.add(value);
      }

    }


    this.form.reset();
    this.snackBar.open("Podaci su sacuvani", undefined, {
      duration: 2000,
    });
    this.close();


  }
  close() {
    this.dialogRef.close();
  }
}
