import { Airport } from "../interfaces/Airport";
import { FormControl } from "@angular/forms";

export class MultiFlight {
    originAirports: Airport[];
    destinationAirports: Airport[];
    originAirportControl: FormControl;
    destinationAirportControl: FormControl;
    departureDate: Date;
    minDate:Date;

    constructor(originAirports: Airport[],destinationAirports: Airport[],originAirportControl: FormControl, destinationAirportControl: FormControl, departureDate: Date, minDate:Date){
        this.originAirports = originAirports;
        this.destinationAirports = destinationAirports;
        this.originAirportControl = originAirportControl;
        this.destinationAirportControl = destinationAirportControl;
        this.departureDate = departureDate;
        this.minDate = minDate;
    }
}