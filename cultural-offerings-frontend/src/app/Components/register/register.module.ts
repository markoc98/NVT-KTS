import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule} from "@angular/forms";
import {RegisterComponent} from "./register.component";
import {MatFormFieldModule} from "@angular/material/form-field";


@NgModule({
  declarations: [RegisterComponent],
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule
  ]
})
export class RegisterModule { }
