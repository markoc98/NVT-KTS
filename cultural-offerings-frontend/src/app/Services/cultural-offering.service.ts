import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
const TOKEN_KEY = 'auth-token';


@Injectable({
  providedIn: 'root'
})
export class CulturalOfferingService {

  "pageable": {
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 20,
    "unpaged": false,
    "paged": true
}

  constructor(private http : HttpClient) { }

  getCulturalOfferings() {

    let headers_object = new HttpHeaders().set("Authorization", "Bearer " + window.sessionStorage.getItem(TOKEN_KEY));

    const httpOptions = {
      headers: headers_object
    };
    
    return this.http.get("api/cultural",httpOptions).toPromise();

  }

  deleteCulturalOffering(id: number){
    let headers_object = new HttpHeaders().set("Authorization", "Bearer " + window.sessionStorage.getItem(TOKEN_KEY));

    const httpOptions = {
      headers: headers_object
    }

    return this.http.delete("api/cultural/delete/" + id, httpOptions).toPromise();
  }

  updateCulturalOffering(id: number, data){
    let headers_object = new HttpHeaders().set("Authorization", "Bearer " + window.sessionStorage.getItem(TOKEN_KEY));

    const httpOptions = {
      headers: headers_object
    }

    console.log(data);

    return this.http.put("api/cultural/update/" + id,data, httpOptions).toPromise();
  }
}
