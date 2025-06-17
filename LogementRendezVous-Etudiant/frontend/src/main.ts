import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';
import {provideHttpClient, withFetch} from "@angular/common/http"; // importe les routes que tu viens de créer

bootstrapApplication(AppComponent, {
  providers: [provideRouter(routes),
    provideHttpClient(withFetch()),],
});
