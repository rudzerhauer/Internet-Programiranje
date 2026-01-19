import { Component, OnInit } from '@angular/core';
import { AuthService as RealAuthService } from '../../service/auth-service.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';
  errorMessage: string = '';
  Message : string='';

  ngOnInit(): void {
      
  }
  constructor(
    private readonly authService : RealAuthService,
    private readonly router: Router,
    private readonly snackBar: MatSnackBar
  ) {}

  onSubmit(): void {
    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        const role = this.authService.getRole();

        if(role==='ADMINISTRATOR') {
          this.router.navigate(['/vozila']);
        }
        this.Message = "Uspjestno ste prijavljeni";
        this.snackBar.open(this.Message);
      },
      error: (error) => {
        this.errorMessage = error.error || 'Korisničko ime ili šifra nisu dobri';
        this.snackBar.open(this.errorMessage, 'Zatvori', { duration: 3000 });
      }
    });
  }
}