import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'; // Add HttpClientModule and HTTP_INTERCEPTORS

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConfirmModalComponent } from './confirm-modal/confirm-modal.component';
import { AppMaterialModule } from './app-material/app-material.module/app-material.module';
import { AuthService } from './service/auth-service.service'; // Ensure AuthService is imported
import { VoziloManagementComponent } from './vozilo-management/vozilo-managment.component';
import { CreateVoziloDialogComponent } from './createvozilodialog/createvozilodialog.component';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { VoziloManagementModule } from './vozilo-management/vozilo-management.module';
import { AuthInterceptor } from './auth.interceptor'; // Import the AuthInterceptor

@NgModule({
  declarations: [
    AppComponent,
    ConfirmModalComponent
    
  ],
  imports: [
    BrowserModule,
    HttpClientModule, // Add HttpClientModule for HTTP requests and interceptors
    AppRoutingModule,
    AppMaterialModule,
    FormsModule,
    MatTabsModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    VoziloManagementModule
  ],
  providers: [
    provideClientHydration(withEventReplay()),
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }, // Provide the AuthInterceptor
    AuthService // Ensure AuthService is provided if not already provided in 'root'
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }