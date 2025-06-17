import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'logement',
    loadComponent: () =>
      import('./modules/logement/pages/logement-list/logement-list.component').then(m => m.LogementListComponent),
  },
  {
    path: 'rendez-vous',
    loadComponent: () =>
      import('./modules/rendez-vous/pages/rendez-vous-list/rendez-vous-list.component').then(m => m.RendezVousListComponent),
  },
  {
    path: '',
    redirectTo: 'logement',
    pathMatch: 'full',
  },
  {
    path: '**',
    redirectTo: 'logement',
  },
];
