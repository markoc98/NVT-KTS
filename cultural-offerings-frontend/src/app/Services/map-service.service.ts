import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class MapService{

  private readonly basePath = 'http://localhost:8080/api/cultural';

     constructor(private http : HttpClient) { 
    
   }

   getCulturalOfferings(name:string) {
     return this.http.get(this.basePath + '/search/' + name,{responseType: 'json'} );

   }

}
