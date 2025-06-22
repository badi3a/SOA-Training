import { Component, OnInit } from '@angular/core';
import { RendezVous, RendezVousService } from './rendezvous.service';
import { Logement } from './logement.service';

@Component({
  selector: 'app-rendezvous',
  templateUrl: './rendezvous.component.html',
  styleUrls: ['./rendezvous.component.css'],
})
export class RendezVousComponent implements OnInit {
  rendezvous: RendezVous[] = [];
  rdv: RendezVous = this.emptyRdv();
  isEditMode = false;

  constructor(private rendezVousService: RendezVousService) {}

  ngOnInit() {
    this.loadRendezVous();
  }

  loadRendezVous() {
    this.rendezVousService
      .getAll()
      .subscribe((data) => (this.rendezvous = data));
  }

  onSubmit() {
    if (this.isEditMode) {
      this.rendezVousService.update(this.rdv.id, this.rdv).subscribe(() => {
        this.loadRendezVous();
        this.resetForm();
      });
    } else {
      this.rendezVousService.add(this.rdv).subscribe(() => {
        this.loadRendezVous();
        this.resetForm();
      });
    }
  }

  editRendezVous(r: RendezVous) {
    this.rdv = { ...r, logement: { ...r.logement } };
    this.isEditMode = true;
  }

  deleteRendezVous(id: number) {
    this.rendezVousService.delete(id).subscribe(() => this.loadRendezVous());
  }

  resetForm() {
    this.rdv = this.emptyRdv();
    this.isEditMode = false;
  }

  get isFormValid(): boolean {
    return (
      this.rdv.date.trim() !== '' &&
      this.rdv.heure.trim() !== '' &&
      this.rdv.logement.reference > 0 &&
      this.rdv.numTel.trim() !== ''
    );
  }

  emptyRdv(): RendezVous {
    return {
      id: 0,
      date: '',
      heure: '',
      logement: {
        reference: 0,
        adresse: '',
        delegation: '',
        gouvernorat: '',
        type: '',
        description: '',
        prix: 0,
      },
      numTel: '',
    };
  }
}
