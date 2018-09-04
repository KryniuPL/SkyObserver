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

@Component({
  selector: "app-search-panel",
  templateUrl: "./search-panel.component.html",
  styleUrls: ["./search-panel.component.scss"]
})
export class SearchPanelComponent implements OnInit {
  
  color: string = 'white';
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


  myControl = new FormControl();
  options: string[] = ["Warsaw", "New York", "London"];
  filteredOptions: Observable<string[]>;

  constructor() {

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
    }
    else if(this.selectedJourney.name === 'Multi-city'){
      this.multiTrip = true;
    }
    else {
      this.oneWay = false;
    }
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
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(""),
      map(value => this._filter(value))
    );
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

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(
      option => option.toLowerCase().indexOf(filterValue) === 0
    );
  }
}
