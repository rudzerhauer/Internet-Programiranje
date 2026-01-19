import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VoziloManagementRoutingModule } from './vozilo-management-routing.module';
import { VoziloManagementComponent } from './vozilo-managment.component';
import { AppMaterialModule } from '../app-material/app-material.module/app-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CreateVoziloDialogComponent } from '../createvozilodialog/createvozilodialog.component';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { VoziloService } from '../service/vozilo.service';


@NgModule({
  declarations: [
    VoziloManagementComponent,
    CreateVoziloDialogComponent
  ],
  imports: [
    CommonModule,
    VoziloManagementRoutingModule,
    
 
    AppMaterialModule,
    FormsModule,
    MatFormField,
    ReactiveFormsModule,
    MatFormField,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatIconModule,
    MatDialogModule,
    MatFormFieldModule
  ],
  providers: [
    provideHttpClient(withInterceptorsFromDi()),
    VoziloService
  ]
})
export class VoziloManagementModule { }
