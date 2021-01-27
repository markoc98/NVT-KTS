import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { CultoffTableComponent } from './cultoff-table.component';
import { MatFormField } from '@angular/material/form-field';



@NgModule({
  declarations: [CultoffTableComponent],
  imports: [
    CommonModule,
    MatTableModule,
    NgModule,
    MatFormField
  ],
  exports: [MatTableModule, NgModule, MatFormField]
})
export class CultoffTableModule { }
