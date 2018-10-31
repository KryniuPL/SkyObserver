package com.skyobserver.model;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


public class Flight {

    //must have parameters
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Airport originAirport;
    private Airport destinationAirport;
    private Duration duration;
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


    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public Duration getDuration() {
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

    public static class Builder{
        private LocalDateTime departureTime;
        private LocalDateTime arrivalTime;
        private Airport originAirport;
        private Airport destinationAirport;
        private Duration duration;
        private Price price;
        private Airline airline;
        private Baggage baggage;
        private List<Flight> stops;

        public Builder setDepartureTime(LocalDateTime departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        public Builder setArrivalTime(LocalDateTime arrivalTime) {
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

        public Builder setDuration(Duration duration) {
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
