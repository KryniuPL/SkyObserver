package com.skyobserver.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class MultiFlight {

    private int totalMiles;
    private String journeyDuration;
    private int journeyPrice;
    private List<Flight> flights;

    public MultiFlight(int totalMiles, String journeyDuration, int journeyPrice, List<Flight> flights) {
        this.totalMiles = totalMiles;
        this.journeyDuration = journeyDuration;
        this.journeyPrice = journeyPrice;
        this.flights = flights;
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

    public int getJourneyPrice() {
        return journeyPrice;
    }

    public void setJourneyPrice(int journeyPrice) {
        this.journeyPrice = journeyPrice;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
