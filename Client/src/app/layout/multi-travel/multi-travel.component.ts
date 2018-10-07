import { Component, OnInit } from '@angular/core';
import { MultiFlight } from '../../model/classes/MultiFlight';

@Component({
  selector: 'app-multi-travel',
  templateUrl: './multi-travel.component.html',
  styleUrls: ['./multi-travel.component.scss']
})
export class MultiTravelComponent implements OnInit {

  flights: MultiFlight[] = [];
  startDate= new Date();
  
  constructor() { }

  ngOnInit() {
      this.loadMultiFlights();
  }

  loadMultiFlights(){
    this.flights.push(new MultiFlight('','',new Date()));
    this.flights.push(new MultiFlight('','',new Date()));
  }

  deleteFlight(flight: MultiFlight){
    var indexOfFlight = this.flights.indexOf(flight);
    this.flights.splice(indexOfFlight,1);
  }

  addFlight(){
    this.flights.push(new MultiFlight('','',new Date()));
  }
}
