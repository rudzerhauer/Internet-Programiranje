import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';// Adjusted path
import { LoginComponent } from './login/login.component'; // Adjusted path
import { RegisterComponent } from './register/register.component'; // Add this (adjust path if needed)

const routes: Routes = [
  {
    path: '',
    component: AuthComponent,
    children: [
      {
        path: 'login', // Changed from '' to 'login' for clarity
        component: LoginComponent,
      },
      {
        path: 'register', // New route for RegisterComponent
        component: RegisterComponent,
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }