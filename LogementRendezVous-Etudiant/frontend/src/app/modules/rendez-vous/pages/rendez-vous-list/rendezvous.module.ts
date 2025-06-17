import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms'; // ✅ Importé pour ngModel
import { HttpClientModule } from '@angular/common/http';
import { RendezVousListComponent } from './rendez-vous-list.component';

const routes: Routes = [
  {
    path: '',
    component: RendezVousListComponent,
  },
];

@NgModule({
  declarations: [RendezVousListComponent],
  imports: [
    CommonModule,
    FormsModule,   
    HttpClientModule,             // ✅ Ajouté ici
    RouterModule.forChild(routes)
  ],
})
export class RendezVousModule {}
