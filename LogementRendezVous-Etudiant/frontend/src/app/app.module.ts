import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module'; // ton routing principal
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [AppComponent], // ton composant racine
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, // importe le routing avec lazy loading
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
