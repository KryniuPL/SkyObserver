import { Airport } from "../interfaces/Airport";

export class FlightForm{
    private type: string;
    private originAirport: string;
    private destinationAirport: string;
    private departureDate: string;
    private returnDate: string
    private isDirectOnly: boolean;

	constructor() {
	}

    /**
     * Getter $type
     * @return {string}
     */
	public get $type(): string {
		return this.type;
	}

    /**
     * Getter $originAirport
     * @return {string}
     */
	public get $originAirport(): string {
		return this.originAirport;
	}

    /**
     * Getter $destinationAirport
     * @return {string}
     */
	public get $destinationAirport(): string {
		return this.destinationAirport;
	}

    /**
     * Getter $departureDate
     * @return {string}
     */
	public get $departureDate(): string {
		return this.departureDate;
	}

    /**
     * Setter $type
     * @param {string} value
     */
	public set $type(value: string) {
		this.type = value;
	}

    /**
     * Setter $originAirport
     * @param {string} value
     */
	public set $originAirport(value: string) {
		this.originAirport = value;
	}

    /**
     * Setter $destinationAirport
     * @param {string} value
     */
	public set $destinationAirport(value: string) {
		this.destinationAirport = value;
	}

    /**
     * Setter $departureDate
     * @param {string} value
     */
	public set $departureDate(value: string) {
		this.departureDate = value;
	}
    

    public get $returnDate(): string{
        return this.$returnDate;
    }

    public set $returnDate(value: string){
        this.returnDate = value;
    }


    /**
     * Getter $isDirectOnly
     * @return {boolean}
     */
	public get $isDirectOnly(): boolean {
		return this.isDirectOnly;
	}

    /**
     * Setter $isDirectOnly
     * @param {boolean} value
     */
	public set $isDirectOnly(value: boolean) {
		this.isDirectOnly = value;
	}

}