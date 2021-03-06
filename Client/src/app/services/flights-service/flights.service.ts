import { Injectable } from '@angular/core';
import { baseUrl } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MultiFlight } from 'src/app/model/interfaces/MultiFlight';


@Injectable({
  providedIn: 'root'
})
export class FlightsService {

  getFlightsURL: string = baseUrl + 'api/flights/';

  constructor(private http: HttpClient) {

  }

  getFlights(originAirport: string, destinationAirport: string, departureDate: string, typeOfConnection: string, currency: string) : Observable<MultiFlight[]>{
    let requestUrl = this.getFlightsURL + originAirport + '/' + destinationAirport + '/' + departureDate + '/' + typeOfConnection + '/' + currency;
    console.log(requestUrl);
    return this.http.get<MultiFlight[]>(requestUrl);   
  }

}
