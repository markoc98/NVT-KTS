import { Component, OnInit } from '@angular/core';
import {User} from "../../model/User";
import {Newsletter} from "../../model/Newsletter";
import {IResponse} from "../login/login.component";
import {UserServiceService} from "../../Services/user-service.service";

const USER_KEY = 'auth-user';

export interface UserResponse{
  username: string,
  password:string,
  email: string,
  id: number

}
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  user: UserResponse = {username:"", password:"", email:"",id: 0};

  subscribed: String[] = ["exit","novi exit", "svez exit"];

  constructor(private userService: UserServiceService) { }

  ngOnInit(): void {

    this.getCurrentUser();
  }

  public async getCurrentUser()
  {

    let response: UserResponse;
    try{
      response = await this.userService.getUserById(window.sessionStorage.getItem(USER_KEY)) as UserResponse;
      this.user = response;
    }
    catch(error) {
      console.error(error);
    }
  }

  public async getSubscriptions(){

  }


}


