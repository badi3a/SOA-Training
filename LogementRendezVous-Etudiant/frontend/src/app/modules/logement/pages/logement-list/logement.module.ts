import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LogementListComponent } from './logement-list.component';
import { FormsModule } from '@angular/forms'; // ✅ Importé pour ngModel
import { HttpClientModule } from '@angular/common/http';

const routes: Routes = [
  {
    path: '',
    component: LogementListComponent,
  },
];

@NgModule({
  declarations: [LogementListComponent],
  imports: [
    CommonModule,
    FormsModule,   
    HttpClientModule,             // ✅ Ajouté ici
    RouterModule.forChild(routes)
  ],
})
export class LogementModule {}
