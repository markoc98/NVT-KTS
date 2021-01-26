import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
const TOKEN_KEY = 'auth-token'

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  //private readonly basePath = 'http://localhost:8080/api/reviews';

  constructor(private http: HttpClient) {

   }

   getAllReviews(id : number){

      let headers_object = new HttpHeaders().set("Authorization", "Bearer " + window.sessionStorage.getItem(TOKEN_KEY));

      const httpOptions = {
        headers: headers_object
      };
      
      return this.http.get("api/reviews/getbycultoff" + id,httpOptions).toPromise();
   }






}


