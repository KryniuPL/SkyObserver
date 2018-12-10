import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { FlightForm } from 'src/app/model/classes/FlightForm';

@Injectable()
export class DataService {

  private messageSource = new BehaviorSubject<FlightForm[]>(new Array<FlightForm>());
  currentMessage = this.messageSource.asObservable();
 
  constructor() { }

  changeMessage(message: FlightForm[]){
    this.messageSource.next(message);
  }
}
