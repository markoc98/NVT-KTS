import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
const TOKEN_KEY = 'auth-token';



@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) { }


  getUserById(userID:string)
  {
    let headers_object = new HttpHeaders().set("Authorization", "Bearer " + window.sessionStorage.getItem(TOKEN_KEY));

    const httpOptions = {
      headers: headers_object
    };

    return this.http.get("api/users/" + userID , httpOptions
    ).toPromise()
  }

}
