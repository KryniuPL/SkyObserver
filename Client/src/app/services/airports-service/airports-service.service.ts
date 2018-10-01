import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { baseUrl } from '../../../environments/environment';
import { Airport } from '../../model/interfaces/Airport';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AirportsService {

  getAirportsUsingIncompletePhraseURL: string = baseUrl + 'api/airports/getAirportsStartingWith/';

  constructor(private http: HttpClient) {

  }

  getAirportsStartingWithPhrase(phrase: string): Observable<Airport[]>{
    return this.http.get<Airport[]>(this.getAirportsUsingIncompletePhraseURL + phrase);   
  }

}
