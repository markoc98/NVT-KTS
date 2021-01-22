import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {LoginModule} from "./Components/login/login.module";
import {HomeModule} from "./Components/home/home.module";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import { MapsComponent } from './Components/maps/maps.component';
import { AgmCoreModule } from '@agm/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    AppComponent,
    MapsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginModule,
    HomeModule,
    BrowserAnimationsModule,
    MatButtonModule,
    AgmCoreModule.forRoot({

      apiKey: 'AIzaSyARCWiYLzvDkzssUFjXZs9i-OzetnexlV4',

      libraries: ['places']

    }),
    NgbModule
  ],
  exports: [AppComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
