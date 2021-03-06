import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { FlightsService } from 'src/app/services/flights-service/flights.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MultiFlight } from 'src/app/model/interfaces/MultiFlight';
import { FlightForm } from 'src/app/model/classes/FlightForm';
import { DataService } from 'src/app/services/data-service/data.service';
import { Router } from '@angular/router';
import { Flight } from 'src/app/model/interfaces/Flight';


@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.scss']
})
export class ResultComponent implements OnInit {

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  panelOpenState = false;
  mockFlights: MultiFlight[];
  choosedFlights: MultiFlight[] = [];
  flightForms: FlightForm[] = [];

  selectFlight(flight: MultiFlight) {
    this.choosedFlights.push(flight)
  }

  constructor(private spinner: NgxSpinnerService, private flightsService: FlightsService, private _formBuilder: FormBuilder, private data: DataService, private router: Router) {

  }

  changeStyleOfBorderTop(element: MultiFlight) {
    element.style = '1px solid #ee4b5e'
  }

  formatDurationExpression(expression: string) {
    let parts = expression.split("H");
    let hours = parts[0].replace("PT", "");
    let minutes = parts[1].replace("M", "");
    return hours + 'h ' + minutes + 'm';
  }

  initializeStepFormGroups(){
    this.flightForms.forEach(element => {
      element.stepFormGroup = this._formBuilder.group({
      })
    });
  }

  isBlank(string): boolean {
    return (!string || /^\s*$/.test(string));
  }

  checkIfThereAreSameAirlinesOnFlight(flights: Flight[]): boolean{
    let airlineName = flights[0].airline.nameAirline;
    let conditionToReturn = false;
    flights.forEach(element => {
      if(element.airline.nameAirline === airlineName){
          conditionToReturn = true;
      }
      else conditionToReturn = false;
    });
    return conditionToReturn;
  }


  ngOnInit() {
    this.spinner.show();
    this.data.currentMessage.subscribe(message => {
      this.flightForms = message;
      this.initializeStepFormGroups();
      this.flightForms.forEach(element => {
        this.flightsService.getFlights(this.formatAirportFormDataToIataCode(element.originAirport), this.formatAirportFormDataToIataCode(element.destinationAirport), element.departureDate, element.isDirectOnly ? 'DIRECT' : 'MORE', 'PLN')
        .toPromise()  
        .then(res => {
            element.displayedFlights = res;
            this.formatFlightDataToDisplay(element.displayedFlights);
            if(this.flightForms.indexOf(element) === this.flightForms.length -1){
              this.spinner.hide();
            }
          }
          ,error => {
            //console.error("NO FLIGHTS WITH GIVEN PARAMETERS" + error);
            element.displayedFlights = [];
            this.spinner.hide();
          })
      });
      //console.log(this.flightForms);
    })
    if (this.flightForms.length === 0) {
      this.router.navigate(['']);
    }
  }

  formatAirportFormDataToIataCode(airportInfo: string){
    var dividedParts = airportInfo.split("\(");
    var lastPart = dividedParts[1];
    var iataCode = lastPart.replace("\)","");
    return iataCode.trim();
  }



  formatFlightDataToDisplay(flightsArray: MultiFlight[]) {
    flightsArray.forEach(element => {
      element.style = 'none';
      element.journeyDuration = this.formatDurationExpression(element.journeyDuration);
      element.departureDate = new Date(element.departureDate);
      element.arrivalDate = new Date(element.arrivalDate);
      element.flights.forEach(subFlight => {
        subFlight.departureTime = new Date(subFlight.departureTime);
        subFlight.arrivalTime = new Date(subFlight.arrivalTime);
      });
    });
  }



}

