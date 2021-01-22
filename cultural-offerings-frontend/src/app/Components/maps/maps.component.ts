import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { environment } from 'src/environments/environment';
import * as Mapboxgl from 'mapbox-gl';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.scss']
})

export class MapsComponent implements OnInit {

  map: Mapboxgl.Map;

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
