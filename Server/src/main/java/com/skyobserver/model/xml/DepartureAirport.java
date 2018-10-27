package com.skyobserver.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DepartureAirport {

    @JacksonXmlProperty(localName = "CodeContext", isAttribute = true)
    private String codeContext;

    @JacksonXmlProperty(localName = "LocationCode", isAttribute = true)
    private String locationCode;

    @JacksonXmlProperty(localName = "FLSLocationName", isAttribute = true)
    private String flsLocationName;

    @JacksonXmlProperty(localName = "Terminal", isAttribute = true)
    private String terminal;

    @JacksonXmlProperty(localName = "FLSDayIndicator", isAttribute = true)
    private String flsDayIndicator;

    public String getCodeContext() {
        return codeContext;
    }

    public void setCodeContext(String codeContext) {
        this.codeContext = codeContext;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getFlsLocationName() {
        return flsLocationName;
    }

    public void setFlsLocationName(String flsLocationName) {
        this.flsLocationName = flsLocationName;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getFlsDayIndicator() {
        return flsDayIndicator;
    }

    public void setFlsDayIndicator(String flsDayIndicator) {
        this.flsDayIndicator = flsDayIndicator;
    }

    @Override
    public String toString() {
        return "DepartureAirport{" +
                "codeContext='" + codeContext + '\'' +
                ", locationCode='" + locationCode + '\'' +
                ", flsLocationName='" + flsLocationName + '\'' +
                ", terminal='" + terminal + '\'' +
                ", flsDayIndicator='" + flsDayIndicator + '\'' +
                '}';
    }
}
