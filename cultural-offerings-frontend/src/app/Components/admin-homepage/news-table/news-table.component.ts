import { Component, OnInit } from '@angular/core';
import { NewsletterServiceService } from 'src/app/Services/newsletter-service.service';


@Component({
  selector: 'app-news-table',
  templateUrl: './news-table.component.html',
  styleUrls: ['./news-table.component.scss']
})
export class NewsTableComponent implements OnInit {

  public newsletters;

  constructor(private newsletterServiceService: NewsletterServiceService) { 
    this.newsletters = [];
  }

  ngOnInit(): void {
    this.getNewsletters();
  }

  public async getNewsletters(){
    this.newsletters = await this.newsletterServiceService.getNewsletters();
    console.log(this.newsletters);
  }
}
