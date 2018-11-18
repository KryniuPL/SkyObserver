import { Flight } from "./Flight";

export interface MultiFlight {
    departureAirportCode: string;
    arrivalAirportCode: string;
    currency: string;
    departureDate: Date;
    arrivalDate: Date;
    totalMiles: number;
    journeyDuration: string;
    journeyPrice: number;
    flights: Flight[];
}