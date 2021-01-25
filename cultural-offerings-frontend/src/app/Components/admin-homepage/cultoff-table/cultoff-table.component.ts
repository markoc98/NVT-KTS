import { Component, OnInit } from '@angular/core';
import { CulturalOfferingService } from 'src/app/Services/cultural-offering.service';

@Component({
  selector: 'app-cultoff-table',
  templateUrl: './cultoff-table.component.html',
  styleUrls: ['./cultoff-table.component.scss']
})



export class CultoffTableComponent implements OnInit {

  public cos;

  constructor(private culturalOfferingService: CulturalOfferingService) {
    this.cos = [];
   }

  ngOnInit(): void {
    this.getCultOffs();
  }


  public async getCultOffs(){
    this.cos = await this.culturalOfferingService.getCulturalOfferings();
    console.log(this.cos);
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



