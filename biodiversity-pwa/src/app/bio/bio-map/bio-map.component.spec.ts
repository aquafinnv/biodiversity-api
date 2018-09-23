import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BioMapComponent } from './bio-map.component';

describe('BioMapComponent', () => {
  let component: BioMapComponent;
  let fixture: ComponentFixture<BioMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BioMapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BioMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
