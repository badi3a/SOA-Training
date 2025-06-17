import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

export interface Logement {
  reference: number;
  adresse: string;
  delegation: string;
  gouvernorat: string;
  type: string;
  description: string;
  prix: number;
}

@Component({
  selector: 'app-logement-list',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './logement-list.component.html',
})
export class LogementListComponent implements OnInit {
  logement: Logement = this.getEmptyLogement();
  logements: Logement[] = [];
  logementsFiltres: Logement[] = [];
  searchTerm: string = '';
  baseUrl = 'http://localhost:8082/LogementRendezVous_Etudiant_war_exploded/api/logement';

  isModification = false;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getAllLogements();
  }

  getEmptyLogement(): Logement {
    return {
      reference: 0,
      adresse: '',
      delegation: '',
      gouvernorat: '',
      type: '',
      description: '',
      prix: 0,
    };
  }

  getAllLogements(): void {
    this.http.get<Logement[]>(this.baseUrl).subscribe({
      next: (data) => {
        this.logements = data;
        this.filtrerLogements();
      },
      error: (err) => console.error('Erreur lors du chargement des logements :', err),
    });
  }

  ajouterLogement(): void {
    this.http.post<Logement>(this.baseUrl, this.logement).subscribe({
      next: (logementAjoute) => {
        this.logements.push(logementAjoute);
        this.filtrerLogements();
        this.logement = this.getEmptyLogement();
        alert('✅ Logement ajouté avec succès !');
      },
      error: (err) => console.error('Erreur lors de l\'ajout :', err),
    });
  }

  supprimerLogement(reference: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce logement ?')) {
      this.http.delete(`${this.baseUrl}/${reference}`, { responseType: 'text' }).subscribe({
        next: (message) => {
          this.logements = this.logements.filter(l => l.reference !== reference);
          this.filtrerLogements();
          alert(message);
        },
        error: (err) => console.error('Erreur lors de la suppression :', err),
      });
    }
  }

  modifierLogement(l: Logement): void {
    this.logement = { ...l };
    this.isModification = true;
  }

  enregistrerModification(): void {
    if (!this.logement.reference) {
      console.warn('Aucune référence à modifier.');
      return;
    }

    this.http.put(`${this.baseUrl}/${this.logement.reference}`, this.logement).subscribe({
      next: () => {
        this.getAllLogements();
        this.logement = this.getEmptyLogement();
        this.isModification = false;
      },
      error: (err) => console.error('Erreur lors de la modification :', err),
    });
  }

  filtrerLogements(): void {
    const terme = this.searchTerm.trim().toLowerCase();
    if (!terme) {
      this.logementsFiltres = [...this.logements];
      return;
    }

    this.logementsFiltres = this.logements.filter(l => {
      const refStr = l.reference.toString().toLowerCase();
      const delegationStr = l.delegation.toLowerCase();
      return refStr.includes(terme) || delegationStr.includes(terme);
    });
  }
}
