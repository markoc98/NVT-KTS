import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {LoginModule} from "./Components/login/login.module";
import {HomeModule} from "./Components/home/home.module";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import { MapsComponent } from './Components/maps/maps.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { FilterCulturalOfferingsComponent } from './Components/filter-cultural-offerings/filter-cultural-offerings.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {UserProfileModule} from './Components/user-profile/user-profile.module';
import { AdminHomepageComponent } from './Components/admin-homepage/admin-homepage.component';
import { AdminHomepageModule } from './Components/admin-homepage/admin-homepage.module';

import { RegisterComponent } from './Components/register/register.component';
import {RegisterModule} from "./Components/register/register.module";
import { EventEmitterService } from './event-emitter.service';
import { MatDialogModule } from '@angular/material/dialog';
import { DialogWindowComponent } from './Components/dialog-window/dialog-window.component';

@NgModule({
  declarations: [
    AppComponent,
    MapsComponent,
    FilterCulturalOfferingsComponent,
    DialogWindowComponent

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
    RegisterModule,
    MatDialogModule,
    AdminHomepageModule,
    RegisterModule

  ],
  exports: [AppComponent],
  providers: [HttpClient,
              EventEmitterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
