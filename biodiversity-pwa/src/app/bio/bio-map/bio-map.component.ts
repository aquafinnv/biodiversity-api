import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { Coordinate } from './coordinate';
import { BioOccurence } from '../../bio/bio-occurence';
import { OccurentMarker } from './occurent-marker';
import { Specie } from '../specie';

@Component({
  selector: 'app-bio-map',
  templateUrl: './bio-map.component.html',
  styleUrls: ['./bio-map.component.css']
})
export class BioMapComponent implements OnChanges {
  @Input()
  character: Coordinate;
  @Input()
  center: Coordinate;
  @Input()
  occurences: BioOccurence[];
  occurenceMarkers: OccurentMarker[];
  @Output()
  onSelect: EventEmitter<BioOccurence> = new EventEmitter<BioOccurence>();

  constructor() { }

  ngOnChanges() {
    if (this.occurences == null) {
      this.occurenceMarkers = [];
    } else {
      this.occurenceMarkers = this.occurences.map(occurence => this.getMarker(occurence));
    }
  }

  getMarker(occurence: BioOccurence): OccurentMarker {
    return {occurence: occurence, sprite: this.getSprite(occurence.species)};
  }

  getSprite(specie: Specie): string {
    if (specie.speciesClass === 'Aves') {
      return 'assets/bird.png';
    } else if (specie.speciesClass === 'Reptilia') {
      return 'assets/reptile.png';
    } else if (specie.speciesClass === 'Agnatha' || specie.speciesClass === 'Chondrichthyes' || specie.speciesClass === 'Osteichthyes') {
      return 'assets/fish.png';
    } else if (specie.speciesClass === 'Mammalia') {
      return 'assets/mammal.png';
    } else {
      return 'assets/bug.png';
    }
  }

}
