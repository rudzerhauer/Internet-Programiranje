import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog'

@Component({
  selector: 'app-confirm-modal',
  standalone: false,
  templateUrl: './confirm-modal.component.html',
  styleUrl: './confirm-modal.component.css'
})
export class ConfirmModalComponent implements OnInit {

  constructor(private dialogRef : MatDialogRef<ConfirmModalComponent>){}

  ngOnInit(): void {
      
  }
  close() {
      this.dialogRef.close();
  }
  confirm() {
      this.dialogRef.close();
  }

}
