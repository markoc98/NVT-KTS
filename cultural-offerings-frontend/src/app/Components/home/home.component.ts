import { Component, OnInit } from '@angular/core';
import {User} from "../../model/User";
import {TokenStorageService} from "../../Services/token-storage.service";
import {UserServiceService} from "../../Services/user-service.service";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  token: string;
  userID: string;
  constructor(private tokenStorageService: TokenStorageService, private userServiceService: UserServiceService) { }

  ngOnInit(): void {
    this.token = window.sessionStorage.getItem(TOKEN_KEY);
    this.userID = this.tokenStorageService.getUser();
  }

  signOut()
  {
    this.tokenStorageService.signOut();
    this.token = window.sessionStorage.getItem(TOKEN_KEY);
    this.userID = this.tokenStorageService.getUser();
  }


}
