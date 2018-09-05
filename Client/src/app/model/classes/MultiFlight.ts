export class MultiFlight {
    originAirport: String;
    destinationAirport: String;
    departureDate: Date;

    constructor(originAirport: String, destinationAirport: String, departureDate: Date){
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
    }
}