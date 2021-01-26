import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AdminHomepageComponent} from "./admin-homepage.component";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import { FormsModule } from "@angular/forms";
import { CultoffTableComponent } from './cultoff-table/cultoff-table.component';
import { NewsTableComponent } from './news-table/news-table.component';
import { CatTableComponent } from './cat-table/cat-table.component';

@NgModule({
  declarations: [
    AdminHomepageComponent,
    CultoffTableComponent,
    NewsTableComponent,
    CatTableComponent
  ],
  exports: [
    AdminHomepageComponent
  ],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule
  ]
})
export class AdminHomepageModule { }