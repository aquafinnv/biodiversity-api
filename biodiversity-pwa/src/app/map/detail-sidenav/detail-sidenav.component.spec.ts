import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailSidenavComponent } from './detail-sidenav.component';

describe('DetailSidenavComponent', () => {
  let component: DetailSidenavComponent;
  let fixture: ComponentFixture<DetailSidenavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailSidenavComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailSidenavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
