import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'atelier-soa';
  constructor(private router: Router) {}
  goTo(page: string) {
    this.router.navigate(['/' + page]);
  }
}
