import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AuthService} from "../../Services/auth.service";
import {TokenStorageService} from "../../Services/token-storage.service";
import { User} from "../../model/User";

export interface IResponse {
  accessToken: string,
  expiresIn: number,
  userID: string
}


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public username: string;
  public password: string;

  constructor(private Auth: AuthService,
              private TokenStorage: TokenStorageService,
              private router: Router) {

    }

  ngOnInit(): void {
  }

  public async loginUser():Promise<void> {

    let response: IResponse;

    try
    {
      response = await this.Auth.login(this.username,this.password) as IResponse;
      let accessToken = response.accessToken;
      this.TokenStorage.saveToken(accessToken);
      this.TokenStorage.saveUser(response.userID);
      this.goToHomePage();
    }
    catch(error) {
      console.error(error);
    }
  }

  public inputUsername(event) {
    this.username = event.target.value;
  }

  public inputPassword(event) {
    this.password = event.target.value;
  }

  private goToHomePage() {
    this.router.navigate([''])
  }
}
