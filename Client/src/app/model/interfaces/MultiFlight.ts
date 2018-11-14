import { Flight } from "./Flight";

export interface MultiFlight {
    totalMiles: number;
    journeyDuration: string;
    journeyPrice: number;
    flights: Flight[];
}