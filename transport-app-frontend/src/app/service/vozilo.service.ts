import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VoziloService {
  private apiUrl = 'http://localhost:8080/api/vozila';

  constructor(private http:HttpClient) { }


  getVoziloTypes() : Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/types`);
  }

  getVozilaByType(type : String) : Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/by-type/${type}`);
  }
  createVozilo(type: string, vozilo: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/${type}`, vozilo);
  }

  deleteVozilo(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  uploadCsv(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post(`${this.apiUrl}/upload-csv`, formData);
  }
}
