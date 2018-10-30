package com.skyobserver.model;

public class Baggage {

    public static final String INFORMATION_ABOUT_BAGGAGE_NOT_FOUND = "Information about Baggage not found";
    public static Baggage NOT_FOUND = new Baggage(INFORMATION_ABOUT_BAGGAGE_NOT_FOUND, "", "");
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

    @Override
    public String toString() {
        return "Baggage{" +
                "freeBaggageAllowance='" + freeBaggageAllowance + '\'' +
                ", extraBaggageAllowance='" + extraBaggageAllowance + '\'' +
                ", technicalParameters='" + technicalParameters + '\'' +
                '}';
    }
}
