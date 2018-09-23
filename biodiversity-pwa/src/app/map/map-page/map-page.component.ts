import { Component, OnInit } from '@angular/core';
import { BioService } from '../../bio/bio.service';
import { LocationService } from '../location.service';
import { BioOccurence } from '../../bio/bio-occurence';
import { Router } from '@angular/router';
import { Coordinate } from '../../bio/bio-map/coordinate';
import { first, map, switchMap, tap } from 'rxjs/operators';
import { FavouriteService } from '../../core/favourite.service';
import { FavouriteState } from '../../core/favourite-state';
import { Specie } from '../../bio/specie';

@Component({
  selector: 'app-map-page',
  templateUrl: './map-page.component.html',
  styleUrls: ['./map-page.component.css']
})
export class MapPageComponent implements OnInit {
  favourites: FavouriteState[];
  occurences: BioOccurence[];
  character: Coordinate;

  constructor(private service: BioService, private locationService: LocationService, private favouriteService: FavouriteService, private router: Router) { }

  ngOnInit() {
    this.favouriteService.get().pipe(first()).subscribe(favourites => this.favourites = favourites);
    this.locationService
      .track()
      .pipe(
        map(position => position.coords),
        tap(coordinates => this.updatePosition(coordinates)),
        switchMap(coordinates => this.service.findAll(coordinates.latitude, coordinates.longitude).pipe(first())),
        tap(occurences => this.notifyFavourites(occurences)))
      .subscribe(occurences => this.occurences = occurences);
  }

  updatePosition(coord: Coordinates) {
    this.character = {longitude: coord.longitude, latitude: coord.latitude};
  }

  openDetail(occurence: BioOccurence) {
    this.router.navigate(['/map', {outlets:{detail: ['detail', occurence.id]}}]);
  }

  notifyFavourites(occurences: BioOccurence[]) {
    occurences
      .map(occurence => occurence.species)
      .filter((specie, index, self) => self
        .findIndex(specie2 => specie.scientificName === specie2.scientificName) === index)
      .filter(specie => this.favourites
        .some(favourite => specie.scientificName === favourite.scientificName))
      .forEach(specie => this.notifyFavourite(specie));
  }

  notifyFavourite(specie: Specie) {
    Notification.requestPermission(perm => {
      if (perm === 'granted') {
        window.navigator.serviceWorker.ready.then(reg => {
          reg.showNotification('Kijk goed rond', {
            body: `In de buurt kan je een ${specie.vernacularName} terug vinden`
          });
        }, err => console.warn(err));
      }
    });
  }
}
