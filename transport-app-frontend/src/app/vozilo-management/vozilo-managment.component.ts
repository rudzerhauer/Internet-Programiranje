import { Component, OnInit } from '@angular/core';
import { VoziloService } from '../service/vozilo.service';
import { MatDialog } from '@angular/material/dialog';
import { CreateVoziloDialogComponent } from '../createvozilodialog/createvozilodialog.component';

@Component({
  selector: 'app-vozilo-management',
  standalone : false,
  templateUrl : './vozilo-managment.component.html',
  styleUrls: ['./vozilo-managment.component.css']
})
export class VoziloManagementComponent implements OnInit {
  voziloTypes: string[] = [];
  selectedType: string = '';
  vozila: any[] = [];
  displayedColumns: string[] = ['idVozila', 'cijenaNabavke', 'proizvodjac', 'specificField', 'actions'];

  constructor(private voziloService: VoziloService, public dialog: MatDialog) {}

  ngOnInit(): void {
    this.voziloService.getVoziloTypes().subscribe(types => {
      this.voziloTypes = types;
      if (types.length > 0) {
        this.selectedType = types[0];
        this.loadVozila();
      }
    });
  }

  loadVozila(): void {
    if (this.selectedType) {
      this.voziloService.getVozilaByType(this.selectedType).subscribe(vozila => {
        this.vozila = vozila;
      });
    }
  }

  onTabChange(type: string): void {
    this.selectedType = type;
    this.loadVozila();
  }

  openCreateDialog(): void {
    const dialogRef = this.dialog.open(CreateVoziloDialogComponent, {
      data: { type: this.selectedType }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.voziloService.createVozilo(this.selectedType, result).subscribe(() => {
          this.loadVozila();
        });
      }
    });
  }

  deleteVozilo(id: number): void {
    if (confirm('Are you sure you want to delete this vozilo?')) {
      this.voziloService.deleteVozilo(id).subscribe(() => {
        this.loadVozila();
      });
    }
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.voziloService.uploadCsv(file).subscribe(() => {
        this.loadVozila();
      });
    }
  }

  getSpecificField(vozilo: any): string {
    if (this.selectedType === 'Auto') {
      return `Datum Nabavke: ${vozilo.datumNabavke}, Model: ${vozilo.model}, Opis: ${vozilo.opis}`;
    } else if (this.selectedType === 'EBike') {
      return `Autonomija: ${vozilo.autonomija}`;
    } else if (this.selectedType === 'ETrotinet') {
      return `Maks Brzina: ${vozilo.maksBrzina}`;
    }
    return '';
  }
}