import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BioInfoCardComponent } from './bio-info-card/bio-info-card.component';
import { MaterialModule } from '../material/material.module';
import { AgmCoreModule } from '@agm/core';
import { BioMapComponent } from './bio-map/bio-map.component';
import { BioLocationCardComponent } from './bio-location-card/bio-location-card.component';
import { BioTaxonomyCardComponent } from './bio-taxonomy-card/bio-taxonomy-card.component';
import { environment } from '../../environments/environment';

@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    AgmCoreModule.forRoot({apiKey: environment.apiKey})
  ],
  declarations: [BioInfoCardComponent, BioMapComponent, BioLocationCardComponent, BioTaxonomyCardComponent],
  exports: [BioInfoCardComponent, BioMapComponent, BioLocationCardComponent, BioTaxonomyCardComponent]
})
export class BioModule { }
