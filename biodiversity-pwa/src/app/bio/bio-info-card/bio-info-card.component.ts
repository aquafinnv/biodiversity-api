import { Component, Input, OnChanges } from '@angular/core';
import { FavouriteService } from '../../core/favourite.service';
import { Subscription } from 'rxjs/internal/Subscription';
import { map } from 'rxjs/operators';
import { Specie } from '../specie';

@Component({
  selector: 'app-bio-info-card',
  templateUrl: './bio-info-card.component.html',
  styleUrls: ['./bio-info-card.component.css']
})
export class BioInfoCardComponent implements OnChanges {
  @Input()
  specie: Specie;
  isFavourite: boolean = false;
  subscription: Subscription;

  constructor(private favouriteService: FavouriteService) { }

  ngOnChanges() {
    if (this.subscription != null) {
      this.subscription.unsubscribe();
    }
    if (this.specie != null) {
      this.subscription = this.favouriteService.get()
        .pipe(map(states => states.find(state => state.scientificName === this.specie.scientificName)))
        .subscribe(state => this.isFavourite = state != null && state.favourite);
    }
  }

  updateFavourite() {
    this.favouriteService.changeState({scientificName: this.specie.scientificName, favourite: !this.isFavourite});
  }

}
