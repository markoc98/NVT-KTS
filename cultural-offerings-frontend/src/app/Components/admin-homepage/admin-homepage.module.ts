import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AdminHomepageComponent} from "./admin-homepage.component";
import {MatFormFieldControl, MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CultOffAddDialog, CultOffEditDialog, CultoffTableComponent } from './cultoff-table/cultoff-table.component';
import { NewsTableComponent } from './news-table/news-table.component';
import { CatTableComponent } from './cat-table/cat-table.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';

@NgModule({
  declarations: [
    AdminHomepageComponent,
    CultoffTableComponent,
    NewsTableComponent,
    CatTableComponent,
    CultOffEditDialog,
    CultOffAddDialog
  ],
  exports: [
    AdminHomepageComponent,
    MatDialogModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    ReactiveFormsModule
  ],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatDialogModule,
    MatButtonModule,
    MatSelectModule,
    ReactiveFormsModule
  ]
})

export class AdminHomepageModule { }