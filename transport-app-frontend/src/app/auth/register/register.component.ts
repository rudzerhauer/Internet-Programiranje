import { Component } from '@angular/core';
import { AuthService } from '../../service/auth-service.service';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
// register.component.ts
export class RegisterComponent {
  username: string = '';
  password: string = '';
  ime: string = '';
  prezime: string = '';
  brTelefona: string = '';
  email: string = '';
  userType: string = 'KLIJENT'; // Default
  brLicneKarte: string = '';
  uloga: string = '';
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService) {}

  onSubmit() {
    this.successMessage = '';
    this.errorMessage = '';
    this.authService.register(
      this.username, this.password, this.ime, this.prezime, this.brTelefona, this.email, this.userType,
      this.userType === 'KLIJENT' ? this.brLicneKarte : undefined,
      this.userType === 'ZAPOSLENI' ? this.uloga : undefined
    ).subscribe({
      next: (response) => {
        this.successMessage = response;
        this.resetForm();
      },
      error: (err) => {
        this.errorMessage = err.error || 'Registration failed';
      }
    });
  }

  resetForm() {
    this.username = '';
    this.password = '';
    this.ime = '';
    this.prezime = '';
    this.brTelefona = '';
    this.email = '';
    this.brLicneKarte = '';
    this.uloga = '';
  }
}
