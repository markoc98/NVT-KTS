import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AdminHomepageComponent} from "./admin-homepage.component";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import { FormsModule } from "@angular/forms";

@NgModule({
  declarations: [
    AdminHomepageComponent
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