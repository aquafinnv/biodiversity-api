import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MapPageComponent } from './map-page/map-page.component';
import { BioModule } from '../bio/bio.module';
import { Route, RouterModule } from '@angular/router';
import { DetailSidenavComponent } from './detail-sidenav/detail-sidenav.component';
import { CoreModule } from '../core/core.module';
import { MaterialModule } from '../material/material.module';

const routes: Route[] = [{
  path: 'map',
  component: MapPageComponent,
  children: [{path: 'detail/:id', component: DetailSidenavComponent, outlet: 'detail'}]
}];

@NgModule({
  imports: [
    CommonModule,
    BioModule,
    CoreModule,
    RouterModule.forChild(routes),
    MaterialModule
  ],
  declarations: [MapPageComponent, DetailSidenavComponent]
})
export class MapModule { }
