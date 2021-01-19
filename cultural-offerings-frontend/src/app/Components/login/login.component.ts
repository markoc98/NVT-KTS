import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AuthService} from "./auth.service";

export interface IResponse {
  accessToken: string,
  expiresIn: number
}



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {
  public username: string;
  public password: string;

  constructor(private Auth: AuthService) {

    }

  ngOnInit(): void {
  }

  public async loginUser():Promise<void> {
    console.log(this.username,this.password);

    let response: IResponse;
    try
    {
      response = await this.Auth.login(this.username,this.password) as IResponse;
    }
    catch(error) {
      console.error(error);
    }

    let accessToken = response.accessToken;
    console.log(response);
  }

  public inputUsername(event) {
    this.username = event.target.value;
  }
  public inputPassword(event) {
    this.password = event.target.value;
  }
}
