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

@Component({
  selector: "app-search-panel",
  templateUrl: "./search-panel.component.html",
  styleUrls: ["./search-panel.component.scss"]
})
export class SearchPanelComponent implements OnInit {
  selectedJourney: JourneyOption;
  journeyOptions: JourneyOption[] = [];
  selectedClass: FlightClass;
  flightsClasses: FlightClass[] = [];

  myControl = new FormControl();
  options: string[] = ["Warsaw", "New York", "London"];
  filteredOptions: Observable<string[]>;

  constructor() {
    this.loadJourneyOptions();
    this.loadFlightsClasses();
    this.selectedClass = this.flightsClasses.find(
      flightClass => flightClass.name === "Economy"
    );
    this.selectedJourney = this.journeyOptions.find(
      option => option.name === "Round Trip"
    );
  }

  changeTypeOfJourney(option: JourneyOption) {
    this.journeyOptions.forEach(element => {
      element.isSelected = false;
    });
    this.selectedJourney = option;
    this.selectedJourney.isSelected = true;
  }

  changeFlightClass(flightClass: FlightClass) {
    this.flightsClasses.forEach(element => {
      element.isSelected = false;
    });
    this.selectedClass = flightClass;
    this.selectedClass.isSelected = true;
  }

  ngOnInit() {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(""),
      map(value => this._filter(value))
    );
  }

  loadJourneyOptions() {
    this.journeyOptions.push(new JourneyOption("Round Trip", true));
    this.journeyOptions.push(new JourneyOption("One-way", false));
    this.journeyOptions.push(new JourneyOption("Multi-city", false));
  }

  loadFlightsClasses() {
    this.flightsClasses.push(new FlightClass("Economy", true));
    this.flightsClasses.push(new FlightClass("Premium Economy", false));
    this.flightsClasses.push(new FlightClass("Business", false));
    this.flightsClasses.push(new FlightClass("First Class", false));
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(
      option => option.toLowerCase().indexOf(filterValue) === 0
    );
  }
}
