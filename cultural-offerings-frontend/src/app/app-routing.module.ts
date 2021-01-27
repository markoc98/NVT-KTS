import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./Components/login/login.component";
import {AppComponent} from "./app.component";
import {HomeComponent} from "./Components/home/home.component";
import { MapsComponent } from './Components/maps/maps.component';
import { UserProfileComponent } from './Components/user-profile/user-profile.component';
import { AdminHomepageComponent } from './Components/admin-homepage/admin-homepage.component';
import {RegisterComponent} from "./Components/register/register.component";
import { CultoffTableComponent } from './Components/admin-homepage/cultoff-table/cultoff-table.component';
import { NewsTableComponent } from './Components/admin-homepage/news-table/news-table.component';
import { CatTableComponent } from './Components/admin-homepage/cat-table/cat-table.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: '', component: MapsComponent},
  {path: 'maps',component:MapsComponent},
  {path: 'user-profile', component:UserProfileComponent},
  {path: 'admin-homepage', component:AdminHomepageComponent},
  {path: 'maps',component:MapsComponent},
  { path:'register',component:RegisterComponent},
  { path:'cultoff-table',component:CultoffTableComponent},
  { path:'news-table',component:NewsTableComponent},
  { path:'cat-table',component:CatTableComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
