import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BioInfoCardComponent } from './bio-info-card.component';

describe('BioInfoCardComponent', () => {
  let component: BioInfoCardComponent;
  let fixture: ComponentFixture<BioInfoCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BioInfoCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BioInfoCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
