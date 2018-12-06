import { Component, OnInit } from '@angular/core';

import { MatDatepickerInputEvent } from '@angular/material';
import { FormControl } from '@angular/forms';
import { Airport } from 'src/app/model/interfaces/Airport';
import { debounceTimeConst } from 'src/environments/environment';
import { AirportsService } from 'src/app/services/airports-service/airports-service.service';
import {debounceTime} from 'rxjs/internal/operators';
import { MultiFlight } from 'src/app/model/interfaces/MultiFlight';
import { FlightForm } from 'src/app/model/classes/FlightForm';
import { MultiFlightOption } from 'src/app/model/classes/MultiFlightOption';

@Component({
  selector: 'app-multi-travel',
  templateUrl: './multi-travel.component.html',
  styleUrls: ['./multi-travel.component.scss']
})
export class MultiTravelComponent implements OnInit {
  checked = true;
  flights: MultiFlightOption[] = [];
  startDate = new Date();

  constructor(private airportService: AirportsService) {}

  ngOnInit() {
    this.loadMultiFlights();
  }

  search() {
    const flightsOptions = new Array<FlightForm>();
    this.flights.forEach(element => {
      const flightFormObject = new FlightForm();
      flightFormObject.$type = 'Multi-Flight';
      flightFormObject.$originAirport = element.originAirportControl.value;
      flightFormObject.$destinationAirport = element.destinationAirportControl.value;
      flightFormObject.$departureDate = this.formatDateObjectToApiFormat(element.departureDate);
      flightsOptions.push(flightFormObject);
    });

    flightsOptions.forEach(element => {
      console.log(element);
    });
  }

  formatDateObjectToApiFormat(date: Date) {
    // 20181120 format for 20-11-2018
    const year = date.getFullYear();
    const month = date.getMonth();
    let day = date.getDay();
    if (day < 10) {
      day = '0' + day;
    }
    return year.toString() + month.toString() + day.toString();
  }

  swapInputValues(originAirportControl: FormControl, destinationAirportControl: FormControl) {
    let tmpSwapper = '';
    const originAirport = originAirportControl.value;
    const destinationAirport = destinationAirportControl.value;
    if (!originAirport || !destinationAirport) {
     return;
    } else {
      tmpSwapper = originAirportControl.value;
      originAirportControl.setValue(destinationAirportControl.value);
      destinationAirportControl.setValue(tmpSwapper);
    }
  }
  reset() {
    this.flights = [];
    this.loadMultiFlights();
  }
  setDateOfMultiTravelOption(flight: MultiFlightOption, event: MatDatepickerInputEvent<Date>) {
      const index = this.flights.indexOf(flight);
      this.flights[index].departureDate = event.value;
      if (index === 0 || (this.flights.length - 1 > index)) {
        this.flights[index + 1].minDate = this.flights[index].departureDate;
      }
  }

  loadMultiFlights() {
   this.createInstanceOfMultiFlightOptions();
   this.createInstanceOfMultiFlightOptions();
  }

  createInstanceOfMultiFlightOptions() {
    // tslint:disable-next-line:max-line-length
    this.flights.push(new MultiFlightOption(new Array<Airport>(), new Array<Airport>(), new FormControl(), new FormControl(), new Date(), new Date()));
    const lastIndexOfArray = this.flights.length - 1;

    if (lastIndexOfArray >= 2) {
      this.flights[lastIndexOfArray].minDate = this.flights[lastIndexOfArray - 1].departureDate;
    }

    this.flights[lastIndexOfArray].originAirportControl.valueChanges.pipe(debounceTime(debounceTimeConst)).subscribe(data => {
      this.airportService.getAirportsStartingWithPhrase(data).subscribe(response => {
        this.flights[lastIndexOfArray].originAirports = response;
      });
    });

    this.flights[lastIndexOfArray].destinationAirportControl.valueChanges.pipe(debounceTime(debounceTimeConst)).subscribe(data => {
      this.airportService.getAirportsStartingWithPhrase(data).subscribe(response => {
        this.flights[lastIndexOfArray].destinationAirports = response;
      });
    });
  }

  deleteFlight(flight: MultiFlightOption) {
    const indexOfFlight = this.flights.indexOf(flight);
    this.flights.splice(indexOfFlight, 1);
  }


}
