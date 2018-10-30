package com.skyobserver.model;

public class Baggage {

    public static Baggage NOT_FOUND = new Baggage("Information about Baggage not found", "", "");
    private String freeBaggageAllowance;
    private String extraBaggageAllowance;
    private String technicalParameters;

    public Baggage(String freeBaggageAllowance, String extraBaggageAllowance, String technicalParameters) {
        this.freeBaggageAllowance = freeBaggageAllowance;
        this.extraBaggageAllowance = extraBaggageAllowance;
        this.technicalParameters = technicalParameters;
    }

    public String getFreeBaggageAllowance() {
        return freeBaggageAllowance;
    }

    public void setFreeBaggageAllowance(String freeBaggageAllowance) {
        this.freeBaggageAllowance = freeBaggageAllowance;
    }

    public String getExtraBaggageAllowance() {
        return extraBaggageAllowance;
    }

    public void setExtraBaggageAllowance(String extraBaggageAllowance) {
        this.extraBaggageAllowance = extraBaggageAllowance;
    }

    public String getTechnicalParameters() {
        return technicalParameters;
    }

    public void setTechnicalParameters(String technicalParameters) {
        this.technicalParameters = technicalParameters;
    }
}
