import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { FlightsService } from 'src/app/services/flights-service/flights.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { MultiFlight } from 'src/app/model/classes/MultiFlight';
import flightsJson from '../../../assets/json/directFlightsMock.json';

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
  flights: MultiFlight[];

  constructor(private spinner: NgxSpinnerService,private flightsService: FlightsService, private _formBuilder: FormBuilder) 
  {

  }
 
  ngOnInit() {
    this.flights = flightsJson;
    
    
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
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
