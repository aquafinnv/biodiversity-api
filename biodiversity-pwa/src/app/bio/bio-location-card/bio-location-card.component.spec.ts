import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BioLocationCardComponent } from './bio-location-card.component';

describe('BioLocationCardComponent', () => {
  let component: BioLocationCardComponent;
  let fixture: ComponentFixture<BioLocationCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BioLocationCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BioLocationCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
