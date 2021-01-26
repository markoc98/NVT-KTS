import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Review } from '../model/Review';
const TOKEN_KEY = 'auth-token'

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private readonly basePath = 'http://localhost:8080/api/reviews';

  constructor(private http: HttpClient) {

   }

   getAllReviews(id : number){

      let headers_object = new HttpHeaders().set("Authorization", "Bearer " + window.sessionStorage.getItem(TOKEN_KEY));

      const httpOptions = {
        headers: headers_object
      };

      return this.http.get("api/reviews/getbycultoff" + id,httpOptions).toPromise();
   }

   public setRatingforOffer(review:Review, userId: string, cultOffId:number){
     let headers_object = new HttpHeaders({
       "Authorization": "Bearer " + window.sessionStorage.getItem(TOKEN_KEY),
       'Content-Type': 'application/json'
     });
     const httpOptions = {
       headers: headers_object
     };

    return this.http.post('api/reviews/setRating/'+userId + "/" + cultOffId, JSON.stringify(review),httpOptions);
   }


  public getReviewsForCulturalOffering(cultOffId : number) {
    let headers_object = new HttpHeaders({
      "Authorization": "Bearer " + window.sessionStorage.getItem(TOKEN_KEY),
      'Content-Type': 'application/text'
    });
    const httpOptions = {
      headers: headers_object
    };
    return this.http.get('api/reviews/getbycultoff/' + cultOffId, httpOptions).toPromise();
  }
}


