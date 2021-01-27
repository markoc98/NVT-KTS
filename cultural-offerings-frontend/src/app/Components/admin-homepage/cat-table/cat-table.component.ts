import { AfterViewInit, ChangeDetectorRef, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormControl, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { CategoryTypeService } from 'src/app/Services/category-type.service';
import { CategoryService } from 'src/app/Services/category.service';


export class CategoryType{
  id: number;
  name: string;
  categoryName: string;
}


export class Category{
  id:number;
  name:string
}

export class CategoryContent{
  "content" : Category[]
}

@Component({
  selector: 'app-cat-table',
  templateUrl: './cat-table.component.html',
  styleUrls: ['./cat-table.component.scss']
})

export class CatTableComponent implements OnInit, AfterViewInit {
  dataSource: MatTableDataSource<CategoryType>;
  displayedColumns: string[] = ['id', 'name', 'categoryName']

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public dialog: MatDialog, private categoryService: CategoryService, private categoryTypeService: CategoryTypeService, private router: Router, private ref: ChangeDetectorRef) {
    this.dataSource = new MatTableDataSource<CategoryType>();
  }

  ngOnInit(): void {
    this.getCategoryTypes();
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

  public async createCategoryType(data){
    let dataName = {
      "name" : data.name
    }
    return this.categoryTypeService.createCategoryTypeByCategoryId(data.category.id, dataName);
  }

  public async getCategoryTypes(){
    let categories = await this.categoryService.getCategories() as CategoryContent; 

    let promises = categories.content.map(async element => {
      const promise = await this.categoryTypeService.getCategoryTypesById(element.id) as CategoryType[];
      promise.forEach(temp => temp.categoryName = element.name)
      console.log(promise);
      return promise;
    });    
    
    const data = await Promise.all(promises);
    this.dataSource.data = data.reduce((a, b) => a.concat(b), []);
  }

  openAddDialog(): void {
    const dialogRef = this.dialog.open(CatAddDialog, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(async result => {
      if(result != undefined)
      {
        let responseData = await this.createCategoryType(result) as CategoryType;
        responseData.categoryName = result.category.name;
        this.dataSource.data.push(responseData);
        this.dataSource.data = this.dataSource.data;
      }
    });
  }
}

export class AddDialogData{
  name: string;
  category: number;
  categoryName: string;
}


@Component({
  selector: 'cat-add-dialog',
  templateUrl: './add-dialog/cat-add-dialog.component.html',
  styleUrls: ['./add-dialog/cat-add-dialog.component.scss']
})
export class CatAddDialog implements OnInit{
  categories: Category[]
  categoryControl = new FormControl('', Validators.required)

  constructor(private categoryService: CategoryService, public dialogRef: MatDialogRef<CatAddDialog>,
    @Inject(MAT_DIALOG_DATA) public data: AddDialogData){}

  ngOnInit(): void {
    this.getCategories();
  }

  public async getCategories(){
    let responseData = await this.categoryService.getCategories() as CategoryContent;
    this.categories = responseData.content;
  }
}
