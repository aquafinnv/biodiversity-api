import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map, switchMap } from 'rxjs/internal/operators';
import { BioService } from '../../bio/bio.service';
import { BioOccurence } from '../../bio/bio-occurence';
import { Coordinate } from '../../bio/bio-map/coordinate';


@Component({
  selector: 'app-detail-sidenav',
  templateUrl: './detail-sidenav.component.html',
  styleUrls: ['./detail-sidenav.component.css']
})
export class DetailSidenavComponent implements OnInit {
  detail: BioOccurence;
  details: BioOccurence[];
  center: Coordinate;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private bioService: BioService) { }

  ngOnInit() {
    this.activatedRoute.params
      .pipe(map(params => params['id']), switchMap(id => this.bioService.findOne(id)))
      .subscribe(detail => {
        this.detail = detail;
        this.details = [detail];
        this.center = {longitude: detail.longitude, latitude: detail.latitude};
      });
  }

  openMap(){
    this.router.navigate(['/map', {outlets:{detail: 'none'}}]);
  }
}
