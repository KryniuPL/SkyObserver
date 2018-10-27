package com.skyobserver.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class FlightDetails {

    @JacksonXmlProperty(localName = "TotalFlightTime", isAttribute = true)
    private String totalFlightTime;

    @JacksonXmlProperty(localName = "TotalMiles", isAttribute = true)
    private String totalMiles;

    @JacksonXmlProperty(localName = "TotalTripTime", isAttribute = true)
    private String totalTripTime;

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
    private String flsFligtDays;

    @JacksonXmlProperty(localName = "FLSDayIndicator", isAttribute = true)
    private String flsDayIndicator;

    @JacksonXmlElementWrapper(localName = "FlightLegDetails")
    private List<FlightLegDetails> flightLegDetailsList;
}
