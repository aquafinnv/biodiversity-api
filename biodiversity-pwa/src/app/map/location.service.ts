import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { throwError } from 'rxjs/internal/observable/throwError';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor() { }

  track(): Observable<Position> {
    if (navigator.geolocation) {
      return new Observable<Position>(observer => {
        const id: number = navigator.geolocation.watchPosition(position => {
          observer.next(position);
        });
        return () => navigator.geolocation.clearWatch(id);
      });
    } else {
      return throwError('Geolocation is not supported');
    }
  }
}
