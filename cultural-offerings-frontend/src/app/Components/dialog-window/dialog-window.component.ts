import { Component, OnInit,Input,Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-window',
  templateUrl: './dialog-window.component.html',
  styleUrls: ['./dialog-window.component.scss']
})
export class DialogWindowComponent implements OnInit {

  @Input() entity : any;

  public name : string;
  public location : string;
  public description : string;
  public rating : number;
   
  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: any) { 
    this.name = dialogData.name;
    this.location = dialogData.location;
    this.description = dialogData.description;
    this.rating = dialogData.rating; 
    
  }

  ngOnInit(): void {
    
  }

}
