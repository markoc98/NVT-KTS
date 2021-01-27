import { AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormControl, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NewsletterFullModel } from 'src/app/model/Newsletter';
import { CulturalOfferingService } from 'src/app/Services/cultural-offering.service';
import { NewsletterServiceService } from 'src/app/Services/newsletter-service.service';

export interface NewsletterContent{
  "content": NewsletterFullModel[]
}

@Component({
  selector: 'app-news-table',
  templateUrl: './news-table.component.html',
  styleUrls: ['./news-table.component.scss']
})
export class NewsTableComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['id', 'title', 'content', "cultOff", "categoryType"]
  dataSource: MatTableDataSource<NewsletterFullModel>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private newsletterService: NewsletterServiceService, public dialog: MatDialog, private router: Router, private ref: ChangeDetectorRef) { 
    this.dataSource = new MatTableDataSource<NewsletterFullModel>();
  }

  ngOnInit(): void {
    this.getNewsletters();
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

  public async getNewsletters(){
    let responseData = await this.newsletterService.getNewsletters() as NewsletterContent;
    this.dataSource.data = responseData.content;
  }

  public async createNewsLetter(data: AddDialogData){
    let cultOffId = data.cultOff;
    let content = {
      "content" : data.content,
      "title" : data.title
    }
    return this.newsletterService.createNewsLetter(cultOffId, content);
  }

  openAddDialog(): void {
    const dialogRef = this.dialog.open(NewsAddDialog, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(async result => {
      if(result != undefined)
      {
        let responseData = await this.createNewsLetter(result) as NewsletterFullModel;
        this.dataSource.data.push(responseData);
        this.dataSource.data = this.dataSource.data;
      }
    });
  }
}

export class AddDialogData{
  title: string;
  content: string;
  cultOff: number;
}

export class CultOff{
  id: number;
  name: string;
}

export class CultOffList{
  "content" : CultOff[]
}

@Component({
  selector: 'news-add-dialog',
  templateUrl: './add-dialog/news-add-dialog.component.html',
  styleUrls: ['./add-dialog/news-add-dialog.component.scss']
})
export class NewsAddDialog implements OnInit{
  cultOfferings: CultOff[]
  cultOffControl = new FormControl('', Validators.required)

  constructor(private cultOffService: CulturalOfferingService,public dialogRef: MatDialogRef<NewsAddDialog>,
    @Inject(MAT_DIALOG_DATA) public data: AddDialogData){}

  ngOnInit(): void{
    this.getCultOffs();
  }

  public async getCultOffs(){
    let responseData = await this.cultOffService.getCulturalOfferings() as CultOffList;
    this.cultOfferings = responseData.content;
    console.log(this.cultOfferings);
  }
}