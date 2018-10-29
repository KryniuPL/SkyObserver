package com.skyobserver.model;

public class Airline {

    private Long id;
    private String nameAirline;
    private String codeIataAirline;
    private String iataPrefixAccounting;
    private String codeIcaoAirline;
    private String callsign;
    private String type;
    private String statusAirline;
    private String founding;
    private String codeHub;
    private String nameCountry;
    private String codeIso2Country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAirline() {
        return nameAirline;
    }

    public void setNameAirline(String nameAirline) {
        this.nameAirline = nameAirline;
    }

    public String getCodeIataAirline() {
        return codeIataAirline;
    }

    public void setCodeIataAirline(String codeIataAirline) {
        this.codeIataAirline = codeIataAirline;
    }

    public String getIataPrefixAccounting() {
        return iataPrefixAccounting;
    }

    public void setIataPrefixAccounting(String iataPrefixAccounting) {
        this.iataPrefixAccounting = iataPrefixAccounting;
    }

    public String getCodeIcaoAirline() {
        return codeIcaoAirline;
    }

    public void setCodeIcaoAirline(String codeIcaoAirline) {
        this.codeIcaoAirline = codeIcaoAirline;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatusAirline() {
        return statusAirline;
    }

    public void setStatusAirline(String statusAirline) {
        this.statusAirline = statusAirline;
    }

    public String getFounding() {
        return founding;
    }

    public void setFounding(String founding) {
        this.founding = founding;
    }

    public String getCodeHub() {
        return codeHub;
    }

    public void setCodeHub(String codeHub) {
        this.codeHub = codeHub;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getCodeIso2Country() {
        return codeIso2Country;
    }

    public void setCodeIso2Country(String codeIso2Country) {
        this.codeIso2Country = codeIso2Country;
    }
}
