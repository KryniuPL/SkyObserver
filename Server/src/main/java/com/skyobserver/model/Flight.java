package com.skyobserver.model;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


public class Flight {

    //must have parameters
    private String status;
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
}
