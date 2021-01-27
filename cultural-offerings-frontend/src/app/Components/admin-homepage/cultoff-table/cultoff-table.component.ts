import { AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, DebugElement, Inject, OnInit, ViewChild} from '@angular/core';
import { CulturalOfferingService } from 'src/app/Services/cultural-offering.service';
import { ContentCultOff, CulturalOfferFullModel} from "../../../model/offer-model";

import {MatTableModule} from '@angular/material/table'
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { pipe } from 'rxjs';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';


@Component({
  selector: 'app-cultoff-table',
  templateUrl: './cultoff-table.component.html',
  styleUrls: ['./cultoff-table.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})


export class CultoffTableComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['id', 'name', 'location', "category", "rating", "description", "edit", "delete"];
  dataSource: MatTableDataSource<CulturalOfferFullModel>;
  

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private culturalOfferingService: CulturalOfferingService, private Router: Router, public dialog: MatDialog, private ref: ChangeDetectorRef) {
    this.dataSource = new MatTableDataSource<CulturalOfferFullModel>();
  }
  
  ngOnInit(): void {   
    this.getCultOffs();    
  }

  ngAfterViewInit(): void{   
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  public async deleteCultural(id) {
    try{      
      await this.culturalOfferingService.deleteCulturalOffering(id);
      const recordIndex = this.dataSource.data.findIndex(obj => obj.id == id);
      this.dataSource.data.splice(recordIndex, 1);
      this.dataSource.data = this.dataSource.data;       
    }catch(error){
      alert("Cultural offering can't be deleted because there are users subscribed to it.");
    }
  }

  public async getCultOffs(){
    let responseData = await this.culturalOfferingService.getCulturalOfferings() as ContentCultOff;
    this.dataSource.data = responseData.content;
  }

  openEditDialog(id): void {
    const dialogRef = this.dialog.open(CultOffEditDialog, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(async result => {
      if(result != undefined)
      {
          let responseData = await this.culturalOfferingService.updateCulturalOffering(id, result) as CulturalOfferFullModel;
          let index = this.dataSource.data.findIndex(obj => obj.id == id)
          this.dataSource.data.splice(index, 1);
          this.dataSource.data.push(responseData);
          this.dataSource.data.sort((a: CulturalOfferFullModel, b:CulturalOfferFullModel) => a.id - b.id);          
          this.dataSource.data = this.dataSource.data;
      }
    });
  }

  openAddDialog(id): void {
    const dialogRef = this.dialog.open(CultOffAddDialog, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(async result => {
      if(result != undefined)
      {
          // let responseData = await this.culturalOfferingService.updateCulturalOffering(id, result) as CulturalOfferFullModel;
          // let index = this.dataSource.data.findIndex(obj => obj.id == id)
          // this.dataSource.data.splice(index, 1);
          // this.dataSource.data.push(responseData);
          // this.dataSource.data.sort((a: CulturalOfferFullModel, b:CulturalOfferFullModel) => a.id - b.id);          
          // this.dataSource.data = this.dataSource.data;
      }
    });
  }
}

export interface DialogData {
  name:string,
  location: string,
  description: string
}

@Component({
  selector: 'cultoff-edit-dialog',
  templateUrl: './edit-dialog/cultoff-edit-dialog.component.html',
  styleUrls: ['./edit-dialog/cultoff-edit-dialog.component.scss']
})
export class CultOffEditDialog{
  
  constructor(public dialogRef: MatDialogRef<CultOffEditDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData){}

    onNoClick(): void {
      this.dialogRef.close();
    }
}

@Component({
  selector: 'cultoff-add-dialog',
  templateUrl: './add-dialog/cultoff-add-dialog.component.html',
  styleUrls: ['./add-dialog/cultoff-add-dialog.component.scss']
})
export class CultOffAddDialog{
  categories: []
  categoryControl = new FormControl('', Validators.required);

  constructor(public dialogRef: MatDialogRef<CultOffAddDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData){}

    onNoClick(): void {
      this.dialogRef.close();
    }
}