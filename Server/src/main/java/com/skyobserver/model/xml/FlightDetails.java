package com.skyobserver.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class FlightDetails {

    @JacksonXmlProperty(localName = "TotalFlightTime", isAttribute = true)
    private String totalFlightTime;

    @JacksonXmlProperty(localName = "TotalMiles", isAttribute = true)
    private String totalMiles;

    @JacksonXmlProperty(localName = "TotalTripTime", isAttribute = true)
    private String totalTripTime;l

    @JacksonXmlProperty(localName = "FLSDepartureDateTime", isAttribute = true)
    private String flsDepartureDateTime;

    @JacksonXmlProperty(localName = "FLSDepartureTimeOffset", isAttribute = true)
    private String flsDepartureTimeOffset;

    @JacksonXmlProperty(localName = "FLSDepartureCode", isAttribute = true)
    private String flsDepartureCode;

    @JacksonXmlProperty(localName = "FLSDepartureName", isAttribute = true)
    private String flsDepartureName;

    @JacksonXmlProperty(localName = "FLSArrivalDateTime", isAttribute = true)
    private String flsArrivalDateTime;

    @JacksonXmlProperty(localName = "FLSArrivalTimeOffset", isAttribute = true)
    private String flsArrivalTimeOffset;

    @JacksonXmlProperty(localName = "FLSArrivalCode", isAttribute = true)
    private String flsArrivalCode;

    @JacksonXmlProperty(localName = "FLSArrivalName", isAttribute = true)
    private String flsArrivalName;

    @JacksonXmlProperty(localName = "FLSFlightType", isAttribute = true)
    private String flsFlightType;

    @JacksonXmlProperty(localName = "FLSFlightLegs", isAttribute = true)
    private String flsFlightLegs;

    @JacksonXmlProperty(localName = "FLSFlightDays", isAttribute = true)
    private String flsFlightDays;

    @JacksonXmlProperty(localName = "FLSDayIndicator", isAttribute = true)
    private String flsDayIndicator;

    @JacksonXmlProperty(localName = "FlightLegDetails", isAttribute = true)
    private List<FlightLegDetails> flightLegDetailsList;

    public String getTotalFlightTime() {
        return totalFlightTime;
    }

    public void setTotalFlightTime(String totalFlightTime) {
        this.totalFlightTime = totalFlightTime;
    }

    public String getTotalMiles() {
        return totalMiles;
    }

    public void setTotalMiles(String totalMiles) {
        this.totalMiles = totalMiles;
    }

    public String getTotalTripTime() {
        return totalTripTime;
    }

    public void setTotalTripTime(String totalTripTime) {
        this.totalTripTime = totalTripTime;
    }

    public String getFlsDepartureDateTime() {
        return flsDepartureDateTime;
    }

    public void setFlsDepartureDateTime(String flsDepartureDateTime) {
        this.flsDepartureDateTime = flsDepartureDateTime;
    }

    public String getFlsDepartureTimeOffset() {
        return flsDepartureTimeOffset;
    }

    public void setFlsDepartureTimeOffset(String flsDepartureTimeOffset) {
        this.flsDepartureTimeOffset = flsDepartureTimeOffset;
    }

    public String getFlsDepartureCode() {
        return flsDepartureCode;
    }

    public void setFlsDepartureCode(String flsDepartureCode) {
        this.flsDepartureCode = flsDepartureCode;
    }

    public String getFlsDepartureName() {
        return flsDepartureName;
    }

    public void setFlsDepartureName(String flsDepartureName) {
        this.flsDepartureName = flsDepartureName;
    }

    public String getFlsArrivalDateTime() {
        return flsArrivalDateTime;
    }

    public void setFlsArrivalDateTime(String flsArrivalDateTime) {
        this.flsArrivalDateTime = flsArrivalDateTime;
    }

    public String getFlsArrivalTimeOffset() {
        return flsArrivalTimeOffset;
    }

    public void setFlsArrivalTimeOffset(String flsArrivalTimeOffset) {
        this.flsArrivalTimeOffset = flsArrivalTimeOffset;
    }

    public String getFlsArrivalCode() {
        return flsArrivalCode;
    }

    public void setFlsArrivalCode(String flsArrivalCode) {
        this.flsArrivalCode = flsArrivalCode;
    }

    public String getFlsArrivalName() {
        return flsArrivalName;
    }

    public void setFlsArrivalName(String flsArrivalName) {
        this.flsArrivalName = flsArrivalName;
    }

    public String getFlsFlightType() {
        return flsFlightType;
    }

    public void setFlsFlightType(String flsFlightType) {
        this.flsFlightType = flsFlightType;
    }

    public String getFlsFlightLegs() {
        return flsFlightLegs;
    }

    public void setFlsFlightLegs(String flsFlightLegs) {
        this.flsFlightLegs = flsFlightLegs;
    }

    public String getFlsFlightDays() {
        return flsFlightDays;
    }

    public void setFlsFligtDays(String flsFligtDays) {
        this.flsFlightDays = flsFlightDays;
    }

    public String getFlsDayIndicator() {
        return flsDayIndicator;
    }

    public void setFlsDayIndicator(String flsDayIndicator) {
        this.flsDayIndicator = flsDayIndicator;
    }

    public List<FlightLegDetails> getFlightLegDetailsList() {
        return flightLegDetailsList;
    }

    public void setFlightLegDetailsList(List<FlightLegDetails> flightLegDetailsList) {
        this.flightLegDetailsList = flightLegDetailsList;
    }

    @Override
    public String toString() {
        return "FlightDetails{" +
                "totalFlightTime='" + totalFlightTime + '\'' +
                ", totalMiles='" + totalMiles + '\'' +
                ", totalTripTime='" + totalTripTime + '\'' +
                ", flsDepartureDateTime='" + flsDepartureDateTime + '\'' +
                ", flsDepartureTimeOffset='" + flsDepartureTimeOffset + '\'' +
                ", flsDepartureCode='" + flsDepartureCode + '\'' +
                ", flsDepartureName='" + flsDepartureName + '\'' +
                ", flsArrivalDateTime='" + flsArrivalDateTime + '\'' +
                ", flsArrivalTimeOffset='" + flsArrivalTimeOffset + '\'' +
                ", flsArrivalCode='" + flsArrivalCode + '\'' +
                ", flsArrivalName='" + flsArrivalName + '\'' +
                ", flsFlightType='" + flsFlightType + '\'' +
                ", flsFlightLegs='" + flsFlightLegs + '\'' +
                ", flsFligtDays='" + flsFlightDays + '\'' +
                ", flsDayIndicator='" + flsDayIndicator + '\'' +
                ", flightLegDetailsList=" + flightLegDetailsList +
                '}';
    }
}
