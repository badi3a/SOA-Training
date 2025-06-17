import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, Validators, FormBuilder } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

export interface Logement {
  id?: number;
  ref?: number;
  reference?: number;
  adresse: string;
  delegation?: string;
  gouvernorat?: string;
  description?: string;
  type?: string;
  prix?: number;
}

export interface RendezVous {
  id?: number;
  date: string;
  heure: string;
  logement: Logement;
  numTel: string;
}

@Component({
  selector: 'app-rendez-vous-list',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './rendez-vous-list.component.html',
  styleUrls: ['./rendez-vous-list.component.css']
})
export class RendezVousListComponent implements OnInit {
  apiBaseUrl = 'http://localhost:8082/LogementRendezVous_Etudiant_war_exploded/api/rendezvous';

  rendezVousList: RendezVous[] = [];
  filteredList: RendezVous[] = [];

  logements: Logement[] = [];

  form = this.fb.group({
    date: ['', Validators.required],
    heure: ['', Validators.required],
    logementReference: ['', Validators.required],
    numTel: ['', [Validators.required, Validators.pattern(/^\+?\d{6,15}$/)]],
  });

  isEditing = false;
  editingId: number | null = null;

  searchText = '';

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit() {
    this.loadRendezVous();
    this.loadLogements();
  }

  loadRendezVous() {
    this.http.get<RendezVous[]>(`${this.apiBaseUrl}`).subscribe({
      next: (data) => {
        this.rendezVousList = data;
        this.filteredList = data;
      },
      error: (err) => {
        console.error('Erreur chargement rendez-vous', err);
      }
    });
  }

  loadLogements() {
    this.http.get<Logement[]>('http://localhost:8082/LogementRendezVous_Etudiant_war_exploded/api/logement').subscribe({
      next: (data) => {
        this.logements = data;
      },
      error: (err) => {
        console.error('Erreur chargement logements', err);
      }
    });
  }

  onSubmit() {
    if (this.form.invalid) return;

    const logement = this.logements.find(l => l.reference === +this.form.value.logementReference!);
    if (!logement) {
      alert('Logement invalide');
      return;
    }

    const rdv: RendezVous = {
      date: this.form.value.date!,
      heure: this.form.value.heure!,
      logement: logement,
      numTel: this.form.value.numTel!,
    };

    if (this.isEditing && this.editingId != null) {
      this.http.put(`${this.apiBaseUrl}/${this.editingId}`, rdv).subscribe({
        next: () => {
          this.loadRendezVous();
          this.resetForm();
        },
        error: (err) => {
          alert('Erreur lors de la mise à jour');
          console.error(err);
        }
      });
    } else {
      this.http.post(`${this.apiBaseUrl}`, rdv).subscribe({
        next: () => {
          this.loadRendezVous();
          this.resetForm();
        },
        error: (err) => {
          alert('Erreur lors de l\'ajout');
          console.error(err);
        }
      });
    }
  }

  editRendezVous(rdv: RendezVous) {
    this.isEditing = true;
    this.editingId = rdv.id ?? null;
    this.form.patchValue({
      date: rdv.date,
      heure: rdv.heure,
      //logement: rdv.logement,
      numTel: rdv.numTel,
    });
  }

  deleteRendezVous(id: number) {
    if (confirm('Voulez-vous vraiment supprimer ce rendez-vous ?')) {
      this.http.delete(`${this.apiBaseUrl}/${id}`, { responseType: 'text' }).subscribe({
        next: () => {
          this.rendezVousList = this.rendezVousList.filter(rdv => rdv.id !== id);
          this.filteredList = this.filteredList.filter(rdv => rdv.id !== id);
        },
        error: (err) => {
          alert('Erreur lors de la suppression');
          console.error(err);
        }
      });
    }
  }

  resetForm() {
    this.isEditing = false;
    this.editingId = null;
    this.form.reset();
  }

  filterList() {
    const txt = this.searchText.toLowerCase();
    this.filteredList = this.rendezVousList.filter(rdv =>
      rdv.date.toLowerCase().includes(txt) ||
      rdv.numTel.toLowerCase().includes(txt) ||
      (rdv.logement.adresse.toLowerCase().includes(txt))
    );
  }
}
