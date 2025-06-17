import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogementListComponent } from './logement-list.component';

describe('LogementListComponent', () => {
  let component: LogementListComponent;
  let fixture: ComponentFixture<LogementListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LogementListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LogementListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
