import { Component, OnInit, AfterViewInit, ViewChild, ElementRef, OnChanges } from '@angular/core';
import { environment } from 'src/environments/environment';
import * as Mapboxgl from 'mapbox-gl';
import { MapService } from 'src/app/Services/map-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { stringify } from '@angular/compiler/src/util';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { EventEmitterService } from '../../event-emitter.service';
import { MatDialog } from '@angular/material/dialog';
import {DialogWindowComponent} from '../dialog-window/dialog-window.component';
import {Router} from "@angular/router";

const USER_KEY = 'auth-user';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.scss']
})

export class MapsComponent implements OnInit {


  //public name: string;
  public searchLocation;
  public entities;
  public categoryName;
  public latitude;
  public longitude;
  public currMarker;

  items: any[] = [
    { id: 1, name: 'Events' },
    { id: 2, name: 'Cultural goods' },
    { id: 3, name: 'Institutions' },
  ];
  selected: string = 'Events';

  map: Mapboxgl.Map;
  userLoggedIn: boolean;

  constructor(private mapService : MapService,
      private eventEmitterService: EventEmitterService,
      private dialog: MatDialog,
              private router: Router

    ){
    this.searchLocation = '';
    this.entities = [];
    this.categoryName = this.selected;
    this.currMarker = [];
  }

  ngOnInit() {
    Mapboxgl.accessToken = environment.mapboxKey;
    this.userLoggedIn = this.isUserLogged();

    this.map = new Mapboxgl.Map({
      container: 'map-mapBox',
      style: 'mapbox://styles/mapbox/streets-v11',
      center: [19.8463064,45.2443501], // starting position
      zoom: 15 // starting zoom
    });
    // const marker = new Mapboxgl.Marker() // initialize a new marker
    //   .setLngLat([19.8495081,45.246105]) // Marker [lng, lat] coordinates
    //   .addTo(this.map); // Add the marker to the map
  }
  selectOption(id: number){
    this.categoryName = id;
    // if(this.searchLocation !== ''){
    //   this.searchCulturalOfferings();
    // }

  }

  public isUserLogged(): boolean{
    return !!window.sessionStorage.getItem(USER_KEY);

  }
  searchCulturalOfferings(){
    this.mapService.getCulturalOfferingForMap(this.searchLocation).subscribe((cultOffering) => {
      const tempEntities = Object.values(cultOffering).filter((item) => item.categoryType.name == this.categoryName)
      this.entities = tempEntities;

      if(this.currMarker !== null){
        for(let i=0; i < this.currMarker.length ; i++) {
          this.currMarker[i].remove();
        }
      }

      for(var temp of tempEntities) {
        this.latitude = temp.latitude;
        this.longitude = temp.longitude;
        console.log(this.latitude + ',' + this.longitude)

        let marker = new Mapboxgl.Marker().setLngLat([this.latitude,this.longitude])
                      .setPopup(new Mapboxgl.Popup().setHTML("<h1>"+temp.name+"</h1>"))
                      .addTo(this.map);

        this.currMarker.push(marker);

      }


      console.log(tempEntities);
    },(error : HttpErrorResponse) => {
      alert("Culutral Offering with given location does not exist.")
    });
  }



  onClickTable(entity){
    console.log(entity)
    this.dialog.open(DialogWindowComponent,{width: '80%', height: '80%',data:{
      id: entity.id,
      name: entity.name,
      location : entity.location,
      rating: entity.rating,
      description: entity.description,
    }});


  }

  myProfileRoute() {
    this.router.navigate(['/user-profile']);
  }

  signOut() {
    window.sessionStorage.clear();
    this.router.navigate(['login']);
  }

  loginRoute() {
    this.router.navigate(['/login']);
  }

  registerRoute() {
    this.router.navigate(['/register']);
  }
}