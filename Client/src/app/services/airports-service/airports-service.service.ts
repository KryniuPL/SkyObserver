import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { baseUrl } from '../../../environments/environment';
import { Airport } from '../../model/interfaces/Airport';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AirportsService {

  getAirportsUsingIncompletePhraseURL: string = baseUrl + 'api/airports/getAirportsStartingWith/';

  constructor(private http: HttpClient) {

  }

  getAirportsStartingWithPhrase(phrase: string): Observable<Airport[]>{
    return this.http.get<Airport[]>(this.getAirportsUsingIncompletePhraseURL + phrase).pipe(catchError(this.handleError)); 
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError(
      'Something bad happened; please try again later.');
  }

  
}
