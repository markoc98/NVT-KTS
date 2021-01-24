import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { environment } from 'src/environments/environment';
import * as Mapboxgl from 'mapbox-gl';
import { MapService } from 'src/app/Services/map-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { stringify } from '@angular/compiler/src/util';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.scss']
})

export class MapsComponent implements OnInit {

  //public name: string;
  public searchedName;
  public entities;

  map: Mapboxgl.Map;

  constructor(private mapService : MapService){
    this.searchedName = '';
    this.entities = [];
  }

  searchCulturalOfferings(){
    this.mapService.getCulturalOfferings(this.searchedName).subscribe((name) => {
      console.log(name);
      this.entities = name;

    },(error : HttpErrorResponse) => {
      alert("Culutral Offering with given name does not exist.")
    });
  }

  ngOnInit() {
    Mapboxgl.accessToken = environment.mapboxKey;

    this.map = new Mapboxgl.Map({
      container: 'map-mapBox',
      style: 'mapbox://styles/mapbox/streets-v11',
      center: [19.8463064,45.2443501], // starting position
      zoom: 15 // starting zoom
    });





  }
}
