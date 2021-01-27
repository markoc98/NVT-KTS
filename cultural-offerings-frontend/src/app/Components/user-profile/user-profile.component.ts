import { Component, OnInit } from '@angular/core';
import {User} from "../../model/User";
import {Newsletter} from "../../model/Newsletter";
import {IResponse} from "../login/login.component";
import {UserServiceService} from "../../Services/user-service.service";
import { CuluralOfferModel} from "../../model/offer-model";
import {Router} from "@angular/router";

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

  subscribed: CuluralOfferModel[];

  constructor(private userService: UserServiceService, private router: Router) { }

  ngOnInit(): void {

    this.getCurrentUser();
    this.getSub();
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

  public async getSub(): Promise<void> {

    let response: CuluralOfferModel[];
    try{
      response = await this.userService.getSubscriptions(window.sessionStorage.getItem(USER_KEY)) as CuluralOfferModel[];
      this.subscribed = response;
    }catch(error){
      console.error(error);
    }
  }


  public async unsubscribe(culturalOfferingId: number) : Promise<void> {
    let response: CuluralOfferModel[];
    try{
      response = await this.userService.unsubscribe(window.sessionStorage.getItem(USER_KEY),culturalOfferingId) as CuluralOfferModel[];
    }catch(error){
      console.error(error);
    }
    this.subscribed = response;
  }

  goHome() {
    this.router.navigate(['maps'])
  }
}


