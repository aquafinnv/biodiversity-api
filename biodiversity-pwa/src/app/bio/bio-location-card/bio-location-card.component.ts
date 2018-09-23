import { Component, Input } from '@angular/core';
import { BioOccurence } from '../bio-occurence';

@Component({
  selector: 'app-bio-location-card',
  templateUrl: './bio-location-card.component.html',
  styleUrls: ['./bio-location-card.component.css']
})
export class BioLocationCardComponent {
  @Input()
  occurence: BioOccurence;

  constructor() { }

}
