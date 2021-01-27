import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatFormField } from '@angular/material/form-field';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { NewsTableComponent } from './news-table.component';
import { MatTableModule } from '@angular/material/table';



@NgModule({
  declarations: [NewsTableComponent],
  imports: [
    CommonModule,
    NgModule,
    MatFormField,
    MatTableModule,
    MatDialogModule
  ],
  exports: [
    NgModule, MatTableModule,  MatFormField, MatDialogModule
  ]
})
export class NewsTableModule { }
