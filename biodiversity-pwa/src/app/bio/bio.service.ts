import { Injectable } from '@angular/core';
import { BioOccurence } from './bio-occurence';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BioService {

  constructor(private http: HttpClient) { }

  findAll(latitude: number, longitude: number, bufferZone: number = 1000, localName: boolean = true, page: number = 1, limit: number = 30): Observable<BioOccurence[]> {
    return this.http.get<BioOccurence[]>(`${environment.apiUrl}/biodiversity?latitude=${latitude}&longitude=${longitude}&bufferZoneMeters=${bufferZone}&localName=${localName}&page=${page}&limit=${limit}`);
  }

  findOne(id: number): Observable<BioOccurence> {
    return this.http.get<BioOccurence>(`${environment.apiUrl}/biodiversity/detail/${id}`);
  }
}
