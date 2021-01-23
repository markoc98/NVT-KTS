import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./Components/login/login.component";
import {AppComponent} from "./app.component";
import {HomeComponent} from "./Components/home/home.component";
import { MapsComponent } from './Components/maps/maps.component';
import {RegisterComponent} from "./Components/register/register.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: '', component: HomeComponent},
  {path: 'maps',component:MapsComponent},
  { path:'register',component:RegisterComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
