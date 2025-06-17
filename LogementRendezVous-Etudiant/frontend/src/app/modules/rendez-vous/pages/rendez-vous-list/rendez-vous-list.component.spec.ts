import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RendezVousListComponent } from './rendez-vous-list.component';

describe('RendezVousListComponent', () => {
  let component: RendezVousListComponent;
  let fixture: ComponentFixture<RendezVousListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RendezVousListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RendezVousListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
