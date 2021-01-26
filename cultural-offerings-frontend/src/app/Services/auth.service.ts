import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'

})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(username,password) {
    return this.http.post('/api/auth/login',
      {
        username,
        password

      },
      ).toPromise()
  ;}

  register(username,email,password) {
    return this.http.post('/api/auth/sign-up',
      {
          username,
          email,
          password

      },
    ).toPromise()
      ;}
    
    

}
