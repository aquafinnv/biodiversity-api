import { Specie } from './specie';

export interface BioOccurence {
  id: number;
  longitude: number;
  latitude: number;
  species: Specie;
}
