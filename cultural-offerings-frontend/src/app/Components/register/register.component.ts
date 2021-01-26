import { Component, OnInit } from '@angular/core';
import {FormGroup, FormsModule} from "@angular/forms";
import {IResponse} from "../login/login.component";
import {AuthService} from "../../Services/auth.service";
import {TokenStorageService} from "../../Services/token-storage.service";
import {Router} from "@angular/router";
import {User} from "../../model/User";
import {HttpResponse} from "@angular/common/http";

export interface RegisterResponse {
    user:User,
    http: HttpResponse<any>
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  public username: string;
  public email: string;
  public password: string;
  matSpinner: boolean = false;
  registerForm: FormGroup;

  constructor(private auth:AuthService, private tokenStorageService:TokenStorageService, private router:Router) { }


  ngOnInit(): void {
    this.username = "";
    this.email = "";
    this.password = "";
  }

  public inputUsername(event) {
    this.username = event.target.value;
  }

  public inputPassword(event) {
    this.password = event.target.value;
  }

  public inputEmail(event) {
    this.email = event.target.value;
  }

  public async registerUser():Promise<void> {
    this.matSpinner = true;
    let response: RegisterResponse;
    try {
      response = await this.auth.register(this.username, this.email, this.password) as RegisterResponse;

      alert("Registered Successfully! Check your email for confirmation.")
      this.goToHomePage();
    } catch (error) {
      console.error(error);
      alert("User with that username or email already exists.")
    }

  }
  private goToHomePage() {
    this.router.navigate([''])
  }
}

