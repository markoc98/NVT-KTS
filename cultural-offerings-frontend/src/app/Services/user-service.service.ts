import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
const TOKEN_KEY = 'auth-token';



@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) { }


  getUserById(userID:string) {
    let headers_object = new HttpHeaders().set("Authorization", "Bearer " + window.sessionStorage.getItem(TOKEN_KEY));
    console.log(headers_object);
    const httpOptions = {
      headers: headers_object
    };

    return this.http.get("api/users/" + userID , httpOptions
    ).toPromise();
  }

  public getSubscriptions(userID:string): Promise<any> {
    let headers_object = new HttpHeaders({
      "Authorization": "Bearer " + window.sessionStorage.getItem(TOKEN_KEY),
      'Content-Type': 'application/text'
    })

    const httpOptions = {
      headers: headers_object
    };

    return this.http.get("api/users/getsubs/" + userID , httpOptions
    ).toPromise();
  }

  public unsubscribe(userId:string, culturalOfferingId:number){
    let headers_object = new HttpHeaders({
      "Authorization": "Bearer " + window.sessionStorage.getItem(TOKEN_KEY),
      'Content-Type': 'application/text'
    });

    const httpOptions = {
      headers: headers_object
    };

    return this.http.get("api/users/unsubscribe/" + userId + "/" + culturalOfferingId , httpOptions
    ).toPromise();
  }
}
