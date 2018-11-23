import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { FlightsService } from 'src/app/services/flights-service/flights.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { MultiFlight } from 'src/app/model/classes/MultiFlight';
declare function require(url: string);

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.scss']
})
export class ResultComponent implements OnInit {

  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  panelOpenState = false;
  mockFlights: MultiFlight[];

  constructor(private spinner: NgxSpinnerService,private flightsService: FlightsService, private _formBuilder: FormBuilder) 
  {

  }

  changeStyleOfBorderTop(element: MultiFlight){
    element.style = '1px solid #ee4b5e'
  }
  
  formatDurationExpression(expression: string){
    let parts = expression.split("H");
    let hours = parts[0].replace("PT","");
    let minutes = parts[1].replace("M","");
    return hours + 'h ' + minutes + 'm'; 
  }

  ngOnInit() {
    let json = require('../../../assets/json/directFlightsMock.json');
    this.mockFlights = json;

    this.mockFlights.forEach(element => {
      element.style = 'none';
      element.journeyDuration = this.formatDurationExpression(element.journeyDuration);
      element.departureDate = new Date(element.departureDate);
      element.arrivalDate = new Date(element.arrivalDate);
    });

    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });

    console.log(this.mockFlights);
    
    // this.spinner.show();
 
    // // setTimeout(() => {
    // //   this.spinner.hide();
    // // }, 10);

    // this.flightsService.getFlights('WAW', 'LHR', '20181210', 'DIRECT' , 'PLN')
    // .subscribe(res => {
    //   console.log(res);
    //   this.spinner.hide();
    // })




    
  }

}

