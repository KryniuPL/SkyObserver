package com.skyobserver.model.xml;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@JsonIgnoreProperties({"PrimaryLangID","Version","TransactionIdentifier", "FLSNote", "FLSDevice", "xmlns", "Success","FLSResponseFields"})
public class OTA_AirDetailsRS {

    @JacksonXmlProperty(localName = "FlightDetails")
    private List<FlightDetails> flightDetailsList;
}
