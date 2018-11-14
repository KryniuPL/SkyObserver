import { Component, OnInit } from "@angular/core";
import { MultiFlight } from "../../model/classes/MultiFlight";
import { MatDatepickerInputEvent } from "@angular/material";
import { FormControl } from "@angular/forms";
import { Airport } from "src/app/model/interfaces/Airport";
import { debounceTimeConst } from "src/environments/environment";
import { AirportsService } from "src/app/services/airports-service/airports-service.service";
import {debounceTime} from 'rxjs/internal/operators';

@Component({
  selector: "app-multi-travel",
  templateUrl: "./multi-travel.component.html",
  styleUrls: ["./multi-travel.component.scss"]
})
export class MultiTravelComponent implements OnInit {
  checked = true;
  flights: MultiFlight[] = [];
  startDate = new Date();

  constructor(private airportService: AirportsService) {}

  ngOnInit() {
    this.loadMultiFlights();
  }

  swapInputValues(originAirportControl: FormControl, destinationAirportControl: FormControl){
    let tmpSwapper = "";
    let originAirport = originAirportControl.value;
    let destinationAirport = destinationAirportControl.value;
    if(!originAirport || !destinationAirport){
     return;
    }
    else{
      tmpSwapper = originAirportControl.value;
      originAirportControl.setValue(destinationAirportControl.value);
      destinationAirportControl.setValue(tmpSwapper);
    }
  }
  reset(){
    this.flights=[];
    this.loadMultiFlights();
  }
  setDateOfMultiTravelOption(flight: MultiFlight,event: MatDatepickerInputEvent<Date>){
      let index = this.flights.indexOf(flight);
      this.flights[index].departureDate = event.value;
      if(index === 0 || (this.flights.length-1 > index)){
        this.flights[index + 1].minDate = this.flights[index].departureDate; 
      } 
  }

  loadMultiFlights() {
   this.createInstanceOfMultiFlightOptions();
   this.createInstanceOfMultiFlightOptions();
  }

  createInstanceOfMultiFlightOptions(){
    this.flights.push(new MultiFlight(new Array<Airport>(), new Array<Airport>(), new FormControl(),new FormControl(), new Date(), new Date()));
    let lastIndexOfArray = this.flights.length -1;

    if(lastIndexOfArray >= 2){
      this.flights[lastIndexOfArray].minDate = this.flights[lastIndexOfArray - 1].departureDate;
    }

    this.flights[lastIndexOfArray].originAirportControl.valueChanges.pipe(debounceTime(debounceTimeConst)).subscribe(data =>{
      this.airportService.getAirportsStartingWithPhrase(data).subscribe(response => {
        this.flights[lastIndexOfArray].originAirports = response;
      });
    });

    this.flights[lastIndexOfArray].destinationAirportControl.valueChanges.pipe(debounceTime(debounceTimeConst)).subscribe(data =>{
      this.airportService.getAirportsStartingWithPhrase(data).subscribe(response => {
        this.flights[lastIndexOfArray].destinationAirports = response;
      });
    });
  }
  
  deleteFlight(flight: MultiFlight) {
    var indexOfFlight = this.flights.indexOf(flight);
    this.flights.splice(indexOfFlight, 1);
  }

  
}
