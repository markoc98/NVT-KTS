import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {LoginModule} from "./Components/login/login.module";
import {HomeModule} from "./Components/home/home.module";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { MapsComponent } from './Components/maps/maps.component';
import { FilterCulturalOfferingsComponent } from './Components/filter-cultural-offerings/filter-cultural-offerings.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './Components/register/register.component';
import {RegisterModule} from "./Components/register/register.module";

@NgModule({
  declarations: [
    AppComponent,
    MapsComponent,
    FilterCulturalOfferingsComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LoginModule,
    HomeModule,
    BrowserAnimationsModule,
    MatButtonModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    RegisterModule
  ],
  exports: [AppComponent],
  providers: [HttpClient],
  bootstrap: [AppComponent]
})
export class AppModule { }
