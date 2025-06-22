import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Logement } from './logement.service';

export interface RendezVous {
  id: number;
  date: string;
  heure: string;
  logement: Logement;
  numTel: string;
}

@Injectable({ providedIn: 'root' })
export class RendezVousService {
  private baseUrl =
    'http://localhost:8084/new_logement_war_exploded/api/rendezvous';

  constructor(private http: HttpClient) {}

  getAll(): Observable<RendezVous[]> {
    return this.http.get<RendezVous[]>(`${this.baseUrl}/getall`);
  }

  getById(id: number): Observable<RendezVous> {
    return this.http.get<RendezVous>(`${this.baseUrl}/getbyid/${id}`);
  }

  getByLogementReference(reference: number): Observable<RendezVous[]> {
    return this.http.get<RendezVous[]>(
      `${this.baseUrl}/getbylogementreference/${reference}`
    );
  }

  add(rdv: RendezVous): Observable<string> {
    return this.http.post(`${this.baseUrl}/add`, rdv, { responseType: 'text' });
  }

  update(id: number, rdv: RendezVous): Observable<string> {
    return this.http.put(`${this.baseUrl}/update/${id}`, rdv, {
      responseType: 'text',
    });
  }

  delete(id: number): Observable<string> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`, {
      responseType: 'text',
    });
  }
}
