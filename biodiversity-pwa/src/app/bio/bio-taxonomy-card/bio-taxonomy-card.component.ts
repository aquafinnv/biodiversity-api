import { Component, Input, OnInit } from '@angular/core';
import { Specie } from '../specie';

@Component({
  selector: 'app-bio-taxonomy-card',
  templateUrl: './bio-taxonomy-card.component.html',
  styleUrls: ['./bio-taxonomy-card.component.css']
})
export class BioTaxonomyCardComponent implements OnInit {
  @Input()
  specie: Specie;

  constructor() { }

  ngOnInit() {
  }

}
