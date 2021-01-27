import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/Services/category.service';


@Component({
  selector: 'app-cat-table',
  templateUrl: './cat-table.component.html',
  styleUrls: ['./cat-table.component.scss']
})

export class CatTableComponent implements OnInit {

  public cats;

  constructor(private categoryService: CategoryService) {
    this.cats = [];
   }

  ngOnInit(): void {
    this.getCats();
  }

  public async getCats(){
    this.cats = await this.categoryService.getCategories();
    console.log(this.cats);
  }

}
