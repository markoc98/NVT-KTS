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

   setRatingforOffer(review:Review){
    var headers = new HttpHeaders({'Content-type':'application/json'});

    console.log("U servisu sam!")
    return this.http.post(this.basePath + '/setRating/', JSON.stringify(review),{headers,responseType:'text' as 'json'});
   }





}


