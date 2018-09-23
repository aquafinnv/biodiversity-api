import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BioTaxonomyCardComponent } from './bio-taxonomy-card.component';

describe('BioTaxonomyCardComponent', () => {
  let component: BioTaxonomyCardComponent;
  let fixture: ComponentFixture<BioTaxonomyCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BioTaxonomyCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BioTaxonomyCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
