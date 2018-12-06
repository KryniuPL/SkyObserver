import { Component, OnInit } from "@angular/core";
import {
  FormControl,
  Validators,
  FormBuilder,
  FormGroup
} from "@angular/forms";
import { Observable } from "rxjs";
import { map, startWith } from "rxjs/operators";
import { JourneyOption } from "../../model/classes/JourneyOption";
import { FlightClass } from "../../model/classes/FlightClass";
import { Passenger } from "../../model/classes/Passenger";
import { AirportsService } from "../../services/airports-service/airports-service.service";
import { Airport } from "../../model/interfaces/Airport";
import {debounceTime} from 'rxjs/internal/operators';
import { debounceTimeConst } from "../../../environments/environment";
import { log } from "util";
import { MatDatepickerInputEvent } from "@angular/material";
import { FlightsService } from "src/app/services/flights-service/flights.service";
import { Router } from "@angular/router";
import { DataService } from "src/app/services/data-service/data.service";
import { FlightForm } from "src/app/model/classes/FlightForm";


@Component({
  selector: "app-search-panel",
  templateUrl: "./search-panel.component.html",
  styleUrls: ["./search-panel.component.scss"]
})
export class SearchPanelComponent implements OnInit {
  checked = true;
  oneWay: boolean = false;
  multiTrip: boolean = false;
  selectedJourney: JourneyOption;
  selectedClass: FlightClass;
  journeyOptions: JourneyOption[] = [];
  flightsClasses: FlightClass[] = [];
  allPassengers: Passenger[] = [];
  adults: Passenger[] = [];
  children: Passenger[] = [];
  infants: Passenger[] = [];

  startDate= new Date();

  airports: Airport[] = [];
  destinationAirports: Airport[] = [];

  originAirportControl = new FormControl();
  desitnationAirportControl = new FormControl();

  selectedFlightForm: FlightForm = new FlightForm();

  setOriginAirport(airport: string){
    this.selectedFlightForm.$originAirport = airport;
  }

  setDestinationAirport(airport: string){
    this.selectedFlightForm.$destinationAirport = airport;
  }

  constructor(private airportService: AirportsService, private flightsService: FlightsService, private router: Router, private data: DataService) {
    this.originAirportControl.valueChanges.pipe(debounceTime(debounceTimeConst)).subscribe(data =>{
      this.airportService.getAirportsStartingWithPhrase(data).subscribe(response => {
        this.airports = response;
      });
    });

    this.desitnationAirportControl.valueChanges.pipe(debounceTime(debounceTimeConst)).subscribe(data =>{
      this.airportService.getAirportsStartingWithPhrase(data).subscribe(response => {
        this.destinationAirports = response;
      });
    });

  }

  searchForFlights(){
    this.router.navigate(['/result']);
    // this.selectedFlightForm.$type = this.selectedJourney.name;
    // var formToPass = new Array<FlightForm>();
    // formToPass.push(this.selectedFlightForm);
    // this.data.changeMessage(formToPass);
    // this.data.currentMessage.subscribe(mess => {
    //   console.log(mess);
    // })
  }

  swapInputValues(){
    let tmpSwapper = "";
    let originAirport = this.originAirportControl.value;
    let destinationAirport = this.desitnationAirportControl.value;
    if(!originAirport || !destinationAirport){
     return;
    }
    else{
      tmpSwapper = this.originAirportControl.value;
      this.originAirportControl.setValue(this.desitnationAirportControl.value);
      this.desitnationAirportControl.setValue(tmpSwapper);
    }
  }

  resetStartDate(event: MatDatepickerInputEvent<Date>){
    this.startDate = new Date();
    this.selectedFlightForm.$returnDate = event.value.toString();
  }
  changeStartDate(event: MatDatepickerInputEvent<Date>){
    if(this.oneWay){
      this.selectedFlightForm.$departureDate = event.value.toString();
    }
    else{
      this.startDate = event.value; 
      this.selectedFlightForm.$departureDate = event.value.toString();
    }
  }

  submitPassengersDialog(){
    this.allPassengers = [];
    this.adults.forEach(element => {
      this.allPassengers.push(element);
    });
    this.children.forEach(element => {
      this.allPassengers.push(element);
    });
    this.infants.forEach(element => {
      this.allPassengers.push(element);
    });
  }

  addPassanger(typeOfPassanger: string){
    if(typeOfPassanger === 'Adult'){
      this.adults.push(new Passenger('Adult'));
    }
    else if(typeOfPassanger === 'Child'){
      this.children.push(new Passenger('Child'))
    }
    else{
      this.infants.push(new Passenger('Infant'));
    }
  }

  deletePassenger(typeOfPassanger: string){
    if(typeOfPassanger === 'Adult'){
      this.adults.pop();
    }
    else if(typeOfPassanger === 'Child'){
      this.children.pop()
    }
    else{
      this.infants.pop();
    }
  }

  clearPassangersLists(){
    this.allPassengers = [];
    this.adults = [];
    this.children = [];
    this.infants = [];
  }

  changeTypeOfJourney(option: JourneyOption) {
    this.journeyOptions.forEach(element => {
      element.isSelected = false;
    });
    this.selectedJourney = option;
    this.selectedJourney.isSelected = true;
    if(this.selectedJourney.name === 'One-way'){
      this.oneWay = true
      this.multiTrip = false;
    }
    else if(this.selectedJourney.name === 'Multi-city'){
      this.multiTrip = true;
    
    }
    else {
      this.oneWay = false;
      this.multiTrip = false;
      this.startDate= new Date();
    }
  // this.originAirportControl.setValue()
  }

  changeFlightClass(flightClass: FlightClass) {
    this.flightsClasses.forEach(element => {
      element.isSelected = false;
    });
    this.selectedClass = flightClass;
    this.selectedClass.isSelected = true;
  }

  ngOnInit() {
    this.loadJourneyOptions();
    this.loadFlightsClasses(); 
    this.loadPassengers();
    
  }

  loadJourneyOptions() {
    this.journeyOptions.push(new JourneyOption("Round Trip", true));
    this.journeyOptions.push(new JourneyOption("One-way", false));
    this.journeyOptions.push(new JourneyOption("Multi-city", false));
    this.selectedJourney = this.journeyOptions.find(
      option => option.name === "Round Trip"
    );
  }

  loadFlightsClasses() {
    this.flightsClasses.push(new FlightClass("Economy", true));
    this.flightsClasses.push(new FlightClass("Premium Economy", false));
    this.flightsClasses.push(new FlightClass("Business", false));
    this.flightsClasses.push(new FlightClass("First Class", false));
    this.selectedClass = this.flightsClasses.find(
      flightClass => flightClass.name === "Economy"
    );
  }

  loadPassengers(){
    this.adults.push(new Passenger('Adult'));
    this.allPassengers.push(new Passenger('Adult'));
  }


 
}
