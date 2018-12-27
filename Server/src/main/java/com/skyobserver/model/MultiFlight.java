package com.skyobserver.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class MultiFlight {

    private String departureAirportCode;
    private String arrivalAirportCode;
    private String currency;
    private String departureDate;
    private String arrivalDate;
    private int totalMiles;
    private String journeyDuration;
    private double journeyPrice;
    private List<Flight> flights;

    public MultiFlight(String departureAirportCode, String arrivalAirportCode, String currency, String departureDate, String arrivalDate, int totalMiles, String journeyDuration, double journeyPrice, List<Flight> flights) {
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
        this.currency = currency;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.totalMiles = totalMiles;
        this.journeyDuration = journeyDuration;
        this.journeyPrice = journeyPrice;
        this.flights = flights;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getTotalMiles() {
        return totalMiles;
    }

    public void setTotalMiles(int totalMiles) {
        this.totalMiles = totalMiles;
    }

    public String getJourneyDuration() {
        return journeyDuration;
    }

    public void setJourneyDuration(String journeyDuration) {
        this.journeyDuration = journeyDuration;
    }

    public double getJourneyPrice() {
        return journeyPrice;
    }

    public void setJourneyPrice(double journeyPrice) {
        this.journeyPrice = journeyPrice;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
