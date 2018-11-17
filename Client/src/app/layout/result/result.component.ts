import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { FlightsService } from 'src/app/services/flights-service/flights.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.scss']
})
export class ResultComponent implements OnInit {

  constructor(private spinner: NgxSpinnerService,private flightsService: FlightsService) 
  {

  }
 
  ngOnInit() {
    this.spinner.show();
 
    setTimeout(() => {
      this.spinner.hide();
    }, 3000);

    // this.flightsService.getFlights('WAW', 'LHR', '20181210', 'DIRECT' , 'PLN')
    // .subscribe(res => {
    //   console.log(res);
    //   this.spinner.hide();
    // })




    
  }

}
