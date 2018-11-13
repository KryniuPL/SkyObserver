import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FlightsService {

  getFlightsURL: string = baseUrl + 'api/flights/';

  constructor(private http: HttpClient) {

  }

  getAirportsStartingWithPhrase(originAirport: string, destinationAirport: string, departureDate: string, typeOfConnection: string, currency: string): Observable<MultiFlight[]>{
    return this.http.get<MultiFlight[]>(this.getFlightsURL + '/'phrase);   
  }


}
