import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import {Loader, LoaderOptions} from 'google-maps';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.scss']
})
export class MapsComponent implements OnInit {

  //  options: LoaderOptions = {/* todo */};
  //  loader = new Loader('AIzaSyARCWiYLzvDkzssUFjXZs9i-OzetnexlV4', this.options);
  // map;
  @ViewChild('mapContainer', {static: false}) gmap: ElementRef;

  map: google.maps.Map;
  lat = 45.267136;
  lng = 19.833549;
  coordinates = new google.maps.LatLng(this.lat, this.lng);
  mapOptions: google.maps.MapOptions = {
    center: this.coordinates,
    zoom: 15,
  };
  mapInitializer() {
    this.map = new google.maps.Map(this.gmap.nativeElement, 
    this.mapOptions);
   }
  constructor() { }

  ngOnInit(): void {
    // this.loader.load().then(function (google) {
    //   const map = new google.maps.Map(document.getElementById('map'), {
    //       center: {lat: -34.397, lng: 150.644},
    //       zoom: 8,
    //   });
    // });

    
  }

  ngAfterViewInit() {
    this.mapInitializer();
  }

  // initMap() {
  //   this.map = new google.maps.Map(document.getElementById("map"), {
  //     center: { lat: -34.397, lng: 150.644 },
  //     zoom: 8,
  //   });
  // }




}
