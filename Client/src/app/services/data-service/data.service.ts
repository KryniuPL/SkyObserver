import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { FlightForm } from 'src/app/model/classes/FlightForm';

@Injectable()
export class DataService {

  private messageSource = new BehaviorSubject<FlightForm[]>(new Array<FlightForm>());
  private isDirectOnly = new BehaviorSubject<boolean>(true);
  currentMessage = this.messageSource.asObservable();
  isDirectOnlyMessage = this.isDirectOnly.asObservable();
  constructor() { }

  changeMessage(message: FlightForm[]){
    this.messageSource.next(message);
  }

  changeIsDirectOnlyMessage(flag: boolean)
  {
    this.isDirectOnlyMessage.next(flag);
  }
}
