import { Airport } from "./Airport";
import { Airline } from "./Airline";
import { Baggage } from "./Baggage";


export interface Flight {
    departureTime: string;
    arrivalTime: string
    originAirport: Airport;
    destinationAirport: Airport;
    duration: string;
    price: JSON;
    airline: Airline;
    baggage: Baggage;
}