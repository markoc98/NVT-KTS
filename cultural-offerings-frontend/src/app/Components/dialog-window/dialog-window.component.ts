import { Component, OnInit,Input,Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Review } from 'src/app/model/Review';
import { AuthService } from 'src/app/Services/auth.service';
import { ReviewService } from 'src/app/Services/review.service';
const USER_KEY = 'auth-user';

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
  public review : Review = {rating: 0, comment: "" ,userId : "0", pictures: [], culturalOfferingID: 0};
  public comment : string;
  public newRating;


  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: any,private reviewService : ReviewService,private authService : AuthService) { 
  }

  ngOnInit(): void {
    this.comment = "";
    this.name = this.dialogData.name;
    this.location = this.dialogData.location;
    this.description = this.dialogData.description;
    this.rating = this.dialogData.rating;  
    this.review.culturalOfferingID = this.dialogData.id;
    
  }

  updateRating(newRating : number) {
    this.review.userId = window.localStorage.getItem(USER_KEY);
    this.newRating = newRating;
    console.log(this.newRating);
    console.log("update rating");
  }

  sendReview() {
    
    console.log(this.review);
    this.review.comment = this.comment;
    this.review.userId = window.localStorage.getItem(USER_KEY);
    this.reviewService.setRatingforOffer(this.review).subscribe(success=> {
          console.log("USPESNO POSTAVLJANJE RATINGA");
          
    });

  }
  inputComment(event){
    this.review.comment= event.target.value;
  }

}
