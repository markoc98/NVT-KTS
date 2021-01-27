import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
const TOKEN_KEY = 'auth-token';

@Injectable({
  providedIn: 'root'
})
export class CategoryTypeService {
  constructor(private http : HttpClient) { }


  getCategoryTypesById(id: number)
  {
    let headers_object = new HttpHeaders().set("Authorization", "Bearer " + window.sessionStorage.getItem(TOKEN_KEY));

    const httpOptions = {
      headers: headers_object
    };
    
    return this.http.get("api/categoryTypes/getbycategoryid/" + id,httpOptions).toPromise();
  }

  createCategoryTypeByCategoryId(id: number, data)
  {
    let headers_object = new HttpHeaders().set("Authorization", "Bearer " + window.sessionStorage.getItem(TOKEN_KEY));

    const httpOptions = {
      headers: headers_object
    };
    
    return this.http.post("api/categoryTypes/catId/" + id, data ,httpOptions).toPromise();
  }
}
