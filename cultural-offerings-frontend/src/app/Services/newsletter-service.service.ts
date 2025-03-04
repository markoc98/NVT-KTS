import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
const TOKEN_KEY = 'auth-token';

@Injectable({
  providedIn: 'root'
})
export class NewsletterServiceService {

  constructor(private http : HttpClient) { }

  getNewsletters() {

    let headers_object = new HttpHeaders().set("Authorization", "Bearer " + window.sessionStorage.getItem(TOKEN_KEY));

    const httpOptions = {
      headers: headers_object
    };

    return this.http.get("api/newsletters",httpOptions).toPromise();

  }

  getNewslettersByCoId(culturalOfferId: number) {

    let headers_object = new HttpHeaders({
      "Authorization": "Bearer " + window.sessionStorage.getItem(TOKEN_KEY),
      'Content-Type': 'application/json'
    })
    const httpOptions = {
      headers: headers_object
    };

    return this.http.get("api/newsletters/culturalOffer/" + culturalOfferId ,httpOptions).toPromise();

  }

  createNewsLetter(culturalOfferId: number, data){
    let headers_object = new HttpHeaders({
      "Authorization": "Bearer " + window.sessionStorage.getItem(TOKEN_KEY)
    })
    const httpOptions = {
      headers: headers_object
    };

    return this.http.post("api/newsletters/create/" + culturalOfferId, data ,httpOptions).toPromise();
  }
}
