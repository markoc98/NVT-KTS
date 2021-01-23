import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  user: User = { username: 'Sara', email: 'sara@nesto.com', password: 'bla' };

  newsletters: Newsletter[] = [{name: 'Exit', address: 'Adresa'}];

  constructor() { }

  ngOnInit(): void {
  }

}

class User {
  username: string;
  email: string;
  password: string;
}

class Newsletter{
  name: string;
  address: string;
}
