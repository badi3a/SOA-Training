import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'logement',
    loadChildren: () =>
      import('./modules/logement/pages/logement-list/logement.module').then(m => m.LogementModule),
  },
  {
    path: 'rendez-vous',
    loadChildren: () =>
      import('./modules/rendez-vous/pages/rendez-vous-list/rendezvous.module').then(m => m.RendezVousModule),
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

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
