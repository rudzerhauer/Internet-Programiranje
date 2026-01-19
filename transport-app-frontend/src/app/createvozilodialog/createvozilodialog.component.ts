import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-create-vozilo-dialog',
  standalone : false,
  templateUrl: './createvozilodialog.component.html',
  styleUrls: ['./createvozilodialog.component.css']
})
export class CreateVoziloDialogComponent {
  vozilo: any = {
    type: '',
    cijenaNabavke: 0,
    datumNabavke: '',
    model: '',
    opis: '',
    autonomija: 0,
    maksBrzina: 0
  };

  constructor(
    public dialogRef: MatDialogRef<CreateVoziloDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.vozilo.type = data.type;
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}