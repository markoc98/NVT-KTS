import { Component, OnInit,Input,Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Review } from 'src/app/model/Review';
import { AuthService } from 'src/app/Services/auth.service';
import { ReviewService } from 'src/app/Services/review.service';
import {UserServiceService} from "../../Services/user-service.service";
import {NewsletterServiceService} from "../../Services/newsletter-service.service";
import {Newsletter} from "../../model/Newsletter";
import {User} from "../../model/User";

const USER_KEY = 'auth-user';

export interface PageableNewsletter {
  content:Newsletter[];
}


export interface PageableReviews {
  content:Review[];
}

@Component({
  selector: 'app-dialog-window',
  templateUrl: './dialog-window.component.html',
  styleUrls: ['./dialog-window.component.scss']
})
export class DialogWindowComponent implements OnInit {

  @Input() entity : any;

  public alreadySubbed: Boolean;
  public id: number;
  public name : string;
  public location : string;
  public description : string;
  public rating : number;
  public review : Review = {rating: 0, comment: "" ,userId : "0", pictures: [], culturalOfferingID: 0};
  public comment : string;
  public newRating;
  public newsletters: Newsletter[];
  public reviews: Review[];
  public user: User;

  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;
  loggedIn: boolean;

  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: any,
              private userService:UserServiceService,
              private authService : AuthService,
              private reviewService: ReviewService,
              private newsletterService: NewsletterServiceService) {


  }

  ngOnInit(): void {

    this.getNewsletter();
    this.getReviews();

    this.loggedIn = this.isLoggedIn();
    this.comment = "";
    this.name = this.dialogData.name;
    this.location = this.dialogData.location;
    this.description = this.dialogData.description;
    this.rating = this.dialogData.rating;
    this.review.culturalOfferingID = this.dialogData.id;
    this.getUser();
    this.isSubbed();

  }
  public isLoggedIn(): boolean{
    if(window.sessionStorage.getItem(USER_KEY)){
      return true;
    }else{
      return false;
    }
  }
  updateRating(newRating : number) {

    this.newRating = newRating;
    console.log(this.newRating);
    console.log("update rating");
  }

  sendReview() {
    console.log(this.review);
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);


    this.review.rating = this.newRating;
    this.review.comment = this.comment;
    this.review.userId = window.sessionStorage.getItem(USER_KEY);
    this.reviewService.setRatingforOffer(this.review, window.sessionStorage.getItem(USER_KEY), this.review.culturalOfferingID).subscribe(success=> {
    console.log("USPESNO POSTAVLJANJE RATINGA");

    });

  }
  inputComment(event){
    this.review.comment= event.target.value;

  }

  public async subscribe() {
    let response;
    try{
      response = await this.userService.subscribe(window.sessionStorage.getItem(USER_KEY),this.dialogData.id,);
    }catch(error){
      console.error(error)
    }
    this.alreadySubbed = true;
  }

  public async isSubbed(){
    let response: Boolean;
    try{
      response = await this.userService.isSubbed(window.sessionStorage.getItem(USER_KEY), this.dialogData.id ) as Boolean;
    }catch(error){
      console.error(error)
    };

    this.alreadySubbed = response;

  }

  public async unsubscribe() {
    let response: any;
    try{
      response = await this.userService.unsubscribe(window.sessionStorage.getItem(USER_KEY),this.dialogData.id,);
    }catch(error){
      console.error(error)
    }
    this.alreadySubbed = !this.alreadySubbed;
  }

  public async getNewsletter() {
    let response: PageableNewsletter;
    try{
      response = await this.newsletterService.getNewslettersByCoId(this.dialogData.id) as PageableNewsletter;
    }catch(error){
      console.error(error)
    }
    this.newsletters = response.content;
  }

  public async getReviews() {
    let response: Review[];
    try{
      response = await this.reviewService.getReviewsForCulturalOffering(this.dialogData.id) as Review[];
    }catch(error){
      console.error(error)
    }
    this.reviews = response;
    console.log(this.reviews);

  }

  public async getUser() {
    let response: User;
    try{
      response = await this.userService.getUserById(window.sessionStorage.getItem(USER_KEY)) as User;
    }catch(error){
      console.error(error)
    }
    this.user = response;
    console.log(this.reviews);

  }

  public onFileChanged(event) {
    //Select File
    this.selectedFile = event.target.files[0];
  }
}
