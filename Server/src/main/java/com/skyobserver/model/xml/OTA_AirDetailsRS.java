package com.skyobserver.model.xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@JsonIgnoreProperties({"PrimaryLangID","Version","TransactionIdentifier", "FLSNote", "FLSDevice", "xmlns", "Success","FLSResponseFields","FLSWarning","Errors"})
public class OTA_AirDetailsRS {

    @JacksonXmlProperty(localName = "FlightDetails")
    private List<FlightDetails> flightDetailsList;

    public List<FlightDetails> getFlightDetailsList() {
        return flightDetailsList;
    }

    public void setFlightDetailsList(List<FlightDetails> flightDetailsList) {
        this.flightDetailsList = flightDetailsList;
    }
}
