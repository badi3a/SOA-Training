import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home.component';
import { LogementComponent } from './logement.component';
import { RendezVousComponent } from './rendezvous.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'logement', component: LogementComponent },
  { path: 'rendezvous', component: RendezVousComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
