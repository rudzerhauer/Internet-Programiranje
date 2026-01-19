import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth'; // Adjust to match your Spring Boot URL
  private tokenKey = 'tajna123';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    const body = { username, password };

    return this.http.post<any>(`${this.apiUrl}/login`, body, { headers }).pipe(
      tap(response => {
        if (response && response.token) {
          localStorage.setItem(this.tokenKey, response.token);
        }
      })
    );
  }
  register(username: string, password: string, ime: string, prezime: string, brTelefona: string, email: string, userType: string, brLicneKarte?: string, uloga?: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const body = { username, password, ime, prezime, brTelefona, email, userType, brLicneKarte, uloga };
    return this.http.post<any>(`${this.apiUrl}/register`, body, { headers });
}

getToken(): string | null {
  const token = localStorage.getItem(this.tokenKey);
  if (!token) {
    console.log('No token found in localStorage');
    return null;
  }

  try {
    const decoded: any = jwtDecode(token);
    const exp = decoded.exp;
    if (exp && Date.now() >= exp * 1000) {
      console.log('Token expired, removing from localStorage');
      localStorage.removeItem(this.tokenKey);
      return null;
    }
    console.log('Token is valid:', token);
    return token;
  } catch (error) {
    console.error('Error decoding token:', error);
    return null;
  }
}

  isAdmin() : boolean {
    const token = this.getToken();
    if(!token) return false;
    const decoded: any = jwtDecode(token);
    const roles = decoded.role || [];
    return roles.some((role:any) => role.authority == 'ROLE_ADMINISTRATOR');
    
  }
  getRole(): string | null {
    const token = this.getToken();
    if (!token) return null;

    try {
      const decoded: any = jwtDecode(token);
      const roles = decoded.role || []; // Extract the 'role' claim
      if (roles.length === 0) return null;
      const role = roles[0].authority; // Get the 'authority' field (e.g., "ROLE_ADMINISTRATOR")
      return role ? role.replace('ROLE_', '') : null; // Remove 'ROLE_' prefix (e.g., "ADMINISTRATOR")
    } catch (error) {
      console.error('Error decoding token:', error);
      return null;
    }
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
  

  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }
}


