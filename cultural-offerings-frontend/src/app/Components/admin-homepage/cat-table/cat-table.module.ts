import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CatTableComponent } from './cat-table.component';
import { MatFormField } from '@angular/material/form-field';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';



@NgModule({
  declarations: [CatTableComponent],
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
export class CatTableModule { }
