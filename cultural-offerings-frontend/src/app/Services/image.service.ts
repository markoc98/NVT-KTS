import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

const TOKEN_KEY ='auth-token'

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http : HttpClient) { }

  public uploadReviewImage(){
    let headers_object = new HttpHeaders({
      "Authorization": "Bearer " + window.sessionStorage.getItem(TOKEN_KEY)
    })
    const httpOptions = {
      headers: headers_object
    };
    //return this.http.
  }

}
