import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Logement {
  reference: number;
  adresse: string;
  delegation: string;
  gouvernorat: string;
  type: string;
  description: string;
  prix: number;
}

@Injectable({ providedIn: 'root' })
export class LogementService {
  private baseUrl =
    'http://localhost:8084/new_logement_war_exploded/api/logement';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Logement[]> {
    return this.http.get<Logement[]>(`${this.baseUrl}/getall`);
  }

  getByReference(reference: number): Observable<Logement> {
    return this.http.get<Logement>(
      `${this.baseUrl}/getlogementbyreference/${reference}`
    );
  }

  add(logement: Logement): Observable<string> {
    return this.http.post(`${this.baseUrl}/addlogement`, logement, {
      responseType: 'text',
    });
  }

  update(reference: number, logement: Logement): Observable<string> {
    return this.http.put(
      `${this.baseUrl}/updatelogement/${reference}`,
      logement,
      { responseType: 'text' }
    );
  }

  delete(reference: number): Observable<string> {
    return this.http.delete(`${this.baseUrl}/deletelogement/${reference}`, {
      responseType: 'text',
    });
  }
}
