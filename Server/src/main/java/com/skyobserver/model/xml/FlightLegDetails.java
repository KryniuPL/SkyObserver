package com.skyobserver.model.xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties({"FLSUUIDActualFlight", "FLSDOTDisclosure", "DateChangeNbr"})
public class FlightLegDetails {

    @JacksonXmlProperty(localName = "DepartureDateTime", isAttribute = true)
    private String departureDateTime;

    @JacksonXmlProperty(localName = "FLSDepartureTimeOffset", isAttribute = true)
    private String flsDepartureTimeOffset;

    @JacksonXmlProperty(localName = "ArrivalDateTime", isAttribute = true)
    private String arrivalDateTime;

    @JacksonXmlProperty(localName = "FLSArrivalTimeOffset", isAttribute = true)
    private String flsArrivalTimeOffset;

    @JacksonXmlProperty(localName = "FlightNumber", isAttribute = true)
    private String flightNumber;

    @JacksonXmlProperty(localName = "JourneyDuration", isAttribute = true)
    private String journeyDuration;

    @JacksonXmlProperty(localName = "SequenceNumber", isAttribute = true)
    private String sequenceNumber;

    @JacksonXmlProperty(localName = "LegDistance", isAttribute = true)
    private String legDistance;

    @JacksonXmlProperty(localName = "FLSMeals", isAttribute = true)
    private String flsMeals;

    @JacksonXmlProperty(localName = "FLSInflightServices", isAttribute = true)
    private String flsInflightServices;

    @JacksonXmlProperty(localName = "FLSUUID", isAttribute = true)
    private String flsuuid;

    @JacksonXmlProperty(localName = "DepartureAirport")
    private DepartureAirport departureAirport;

    @JacksonXmlProperty(localName = "ArrivalAirport")
    private ArrivalAirport arrivalAirport;

    @JacksonXmlProperty(localName = "MarketingAirline")
    private MarketingAirline marketingAirline;

    @JacksonXmlProperty(localName = "OperatingAirline")
    private OperatingAirline operatingAirline;

    @JacksonXmlProperty(localName = "Equipment")
    private Equipment equipment;

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getFlsDepartureTimeOffset() {
        return flsDepartureTimeOffset;
    }

    public void setFlsDepartureTimeOffset(String flsDepartureTimeOffset) {
        this.flsDepartureTimeOffset = flsDepartureTimeOffset;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getFlsArrivalTimeOffset() {
        return flsArrivalTimeOffset;
    }

    public void setFlsArrivalTimeOffset(String flsArrivalTimeOffset) {
        this.flsArrivalTimeOffset = flsArrivalTimeOffset;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getJourneyDuration() {
        return journeyDuration;
    }

    public void setJourneyDuration(String journeyDuration) {
        this.journeyDuration = journeyDuration;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getLegDistance() {
        return legDistance;
    }

    public void setLegDistance(String legDistance) {
        this.legDistance = legDistance;
    }

    public String getFlsMeals() {
        return flsMeals;
    }

    public void setFlsMeals(String flsMeals) {
        this.flsMeals = flsMeals;
    }

    public String getFlsInflightServices() {
        return flsInflightServices;
    }

    public void setFlsInflightServices(String flsInflightServices) {
        this.flsInflightServices = flsInflightServices;
    }

    public String getFlsuuid() {
        return flsuuid;
    }

    public void setFlsuuid(String flsuuid) {
        this.flsuuid = flsuuid;
    }

    public DepartureAirport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(DepartureAirport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public ArrivalAirport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(ArrivalAirport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public MarketingAirline getMarketingAirline() {
        return marketingAirline;
    }

    public void setMarketingAirline(MarketingAirline marketingAirline) {
        this.marketingAirline = marketingAirline;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "FlightLegDetails{" +
                "departureDateTime='" + departureDateTime + '\'' +
                ", flsDepartureTimeOffset='" + flsDepartureTimeOffset + '\'' +
                ", arrivalDateTime='" + arrivalDateTime + '\'' +
                ", flsArrivalTimeOffset='" + flsArrivalTimeOffset + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", journeyDuration='" + journeyDuration + '\'' +
                ", sequenceNumber='" + sequenceNumber + '\'' +
                ", legDistance='" + legDistance + '\'' +
                ", flsMeals='" + flsMeals + '\'' +
                ", flsInflightServices='" + flsInflightServices + '\'' +
                ", flsuuid='" + flsuuid + '\'' +
                ", departureAirport=" + departureAirport.toString() +
                ", arrivalAirport=" + arrivalAirport.toString() +
                ", marketingAirline=" + marketingAirline.toString() +
                ", equipment=" + equipment.toString() +
                '}';
    }
}
