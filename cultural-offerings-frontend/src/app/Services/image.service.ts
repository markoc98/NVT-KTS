import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

const TOKEN_KEY ='auth-token'

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http : HttpClient) { }

  public uploadReviewImage(uploadImageData: FormData, reviewId: number){
    let headers_object = new HttpHeaders({
      "Authorization": "Bearer " + window.sessionStorage.getItem(TOKEN_KEY)
    })
    const httpOptions = {
      headers: headers_object
    };
    return this.http.post('api/picture/upload/review/'+ reviewId, uploadImageData, httpOptions).toPromise();
  }

  public getReviewImage(reviewId: number){
    let headers_object = new HttpHeaders({
      "Authorization": "Bearer " + window.sessionStorage.getItem(TOKEN_KEY)
    })
    const httpOptions = {
      headers: headers_object
    };
    return this.http.get('api/picture/review/'+ reviewId, httpOptions).toPromise();
  }

  async getCulturalOfferImage(coId: number) {
    let headers_object = new HttpHeaders({
      "Authorization": "Bearer " + window.sessionStorage.getItem(TOKEN_KEY)
    })
    const httpOptions = {
      headers: headers_object
    };
    return this.http.get('api/picture/culturalOffering/'+ coId, httpOptions).toPromise();
  }
}
