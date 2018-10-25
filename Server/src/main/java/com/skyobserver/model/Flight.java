package com.skyobserver.model;


import java.time.Duration;
import java.time.LocalDateTime;

public class Flight {

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Airport originAirport;
    private Airport destinationAirport;
    private Duration duration;
    private FlightPrice price;
    private Airline airline;

}
