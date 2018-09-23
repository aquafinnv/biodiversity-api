import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { FavouriteState } from './favourite-state';

@Injectable({
  providedIn: 'root'
})
export class FavouriteService {
  subject: BehaviorSubject<FavouriteState[]>;

  constructor() {
    this.subject = new BehaviorSubject<FavouriteState[]>(FavouriteService.getData());
  }

  get(): BehaviorSubject<FavouriteState[]> {
    return this.subject;
  }

  changeState(newState: FavouriteState) {
    const states: FavouriteState[] = FavouriteService.getData();
    const currentState = states.find(state => state.scientificName === newState.scientificName);
    if (currentState == null) {
      states.push(newState);
    } else {
      currentState.favourite = newState.favourite;
    }
    localStorage.setItem('favourites', JSON.stringify(states));
    this.subject.next(states);
  }

  static getData(): FavouriteState[] {
    return JSON.parse(localStorage.getItem('favourites') || '[]');
  }
}
