package com.skyobserver.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class OperatingAirline {

    @JacksonXmlProperty(localName = "Code", isAttribute = true)
    private String code;

    @JacksonXmlProperty(localName = "CodeContext", isAttribute = true)
    private String codeContext;

    @JacksonXmlProperty(localName = "CompanyShortName", isAttribute = true)
    private String companyShortName;

    @JacksonXmlProperty(localName = "FlightNumber", isAttribute = true)
    private String flightNumber;
}
