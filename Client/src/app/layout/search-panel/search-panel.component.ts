import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormBuilder, FormGroup } from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { JourneyOption } from '../../model/classes/JourneyOption';


@Component({
  selector: 'app-search-panel',
  templateUrl: './search-panel.component.html',
  styleUrls: ['./search-panel.component.scss']
})
export class SearchPanelComponent implements OnInit {

  journeyOptions: JourneyOption[] = [];


  myControl = new FormControl();
  options: string[] = ['Warsaw', 'New York', 'London'];
  filteredOptions: Observable<string[]>;

  constructor() {
      this.loadJourneyOptions();
  }


  ngOnInit() {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }

  loadJourneyOptions(){
    this.journeyOptions.push(new JourneyOption('Round Trip', true));
    this.journeyOptions.push(new JourneyOption('One-way', false));
    this.journeyOptions.push(new JourneyOption('Multi-city', false));
  }
  
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().indexOf(filterValue) === 0);
  }
}
