import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-homepage',
  templateUrl: './admin-homepage.component.html',
  styleUrls: ['./admin-homepage.component.scss']
})
export class AdminHomepageComponent implements OnInit {

  cos: CulturalOffering[] = [{name: 'Exit', address: 'Adresa', category: 'Event', type: 'Festival', rating: 5, description: 'Great'}];

  newsletters: Newsletter[] = [{name: 'Exit', address: 'Adresa'}];

  cats: Category[] = [{name: 'Event'}];

  constructor() { }

  ngOnInit(): void {
  }

}

class CulturalOffering{
  name: string;
  address: string;
  category: string;
  type: string;
  rating: number;
  description: string;
}

class Newsletter{
  name: string;
  address: string;
}

class Category{
  name: string;
}


