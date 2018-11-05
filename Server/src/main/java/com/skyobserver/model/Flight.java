package com.skyobserver.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;

@JsonSerialize
public class Flight {

    //must have parameters
    private String departureTime;
    private String arrivalTime;
    private Airport originAirport;
    private Airport destinationAirport;
    private String duration;
    private Price price;
    private Airline airline;
    private Baggage baggage;

    //optional parameters
    private List<Flight> stops;

    private Flight(Builder flightBuilder){
        this.departureTime = flightBuilder.departureTime;
        this.arrivalTime = flightBuilder.arrivalTime;
        this.originAirport = flightBuilder.originAirport;
        this.destinationAirport = flightBuilder.destinationAirport;
        this.duration = flightBuilder.duration;
        this.price = flightBuilder.price;
        this.airline = flightBuilder.airline;
        this.baggage = flightBuilder.baggage;
        this.stops = flightBuilder.stops;
    }


    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public String getDuration() {
        return duration;
    }

    public Price getPrice() {
        return price;
    }

    public Airline getAirline() {
        return airline;
    }

    public Baggage getBaggage() {
        return baggage;
    }

    public List<Flight> getStops() {
        return stops;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", originAirport=" + originAirport.toString() +
                ", destinationAirport=" + destinationAirport.toString() +
                ", duration=" + duration +
                ", price=" + price.toString() +
                ", airline=" + airline.toString() +
                ", baggage=" + baggage.toString() +
                ", stops=" + stops +
                '}';
    }

    @JsonSerialize
    public static class Builder{
        private String departureTime;
        private String arrivalTime;
        private Airport originAirport;
        private Airport destinationAirport;
        private String duration;
        private Price price;
        private Airline airline;
        private Baggage baggage;
        private List<Flight> stops;

        public Builder setDepartureTime(String departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        public Builder setArrivalTime(String arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        public Builder setOriginAirport(Airport originAirport) {
            this.originAirport = originAirport;
            return this;
        }

        public Builder setDestinationAirport(Airport destinationAirport) {
            this.destinationAirport = destinationAirport;
            return this;
        }

        public Builder setDuration(String duration) {
            this.duration = duration;
            return this;
        }

        public Builder setPrice(Price price) {
            this.price = price;
            return this;
        }

        public Builder setAirline(Airline airline) {
            this.airline = airline;
            return this;
        }

        public Builder setBaggage(Baggage baggage) {
            this.baggage = baggage;
            return this;
        }

        public Builder setStops(List<Flight> stops) {
            this.stops = stops;
            return this;
        }

        public Flight build(){
            return new Flight(this);
        }
    }
}
