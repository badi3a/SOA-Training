import { Component, OnInit } from '@angular/core';
import { Logement, LogementService } from './logement.service';

@Component({
  selector: 'app-logement',
  templateUrl: './logement.component.html',
  styleUrls: ['./logement.component.css'],
})
export class LogementComponent implements OnInit {
  logements: Logement[] = [];
  logement: Logement = this.emptyLogement();
  isEditMode = false;
  searchReference: number | null = null;
  searchResult: Logement | null = null;
  searchTriggered = false;

  constructor(private logementService: LogementService) {}

  ngOnInit() {
    this.loadLogements();
  }

  get isFormValid(): boolean {
    return (
      this.logement.reference > 0 &&
      this.logement.adresse.trim() !== '' &&
      this.logement.delegation.trim() !== '' &&
      this.logement.gouvernorat.trim() !== '' &&
      this.logement.type.trim() !== '' &&
      this.logement.description.trim() !== '' &&
      this.logement.prix > 0
    );
  }

  loadLogements() {
    this.logementService.getAll().subscribe((data) => (this.logements = data));
  }

  onSubmit() {
    if (this.isEditMode) {
      this.logementService
        .update(this.logement.reference, this.logement)
        .subscribe(() => {
          this.loadLogements();
          this.resetForm();
        });
    } else {
      this.logementService.add(this.logement).subscribe(() => {
        this.loadLogements();
        this.resetForm();
      });
    }
  }

  editLogement(l: Logement) {
    this.logement = { ...l };
    this.isEditMode = true;
  }

  deleteLogement(reference: number) {
    this.logementService
      .delete(reference)
      .subscribe(() => this.loadLogements());
  }

  resetForm() {
    this.logement = this.emptyLogement();
    this.isEditMode = false;
    this.searchTriggered = false;
  }

  emptyLogement(): Logement {
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

  searchByReference() {
    this.searchTriggered = true;
    if (this.searchReference && this.searchReference > 0) {
      this.logementService.getByReference(this.searchReference).subscribe({
        next: (logement) => (this.searchResult = logement),
        error: () => (this.searchResult = null),
      });
    } else {
      this.searchResult = null;
    }
  }
}
