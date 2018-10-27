package com.skyobserver.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Equipment {

    @JacksonXmlProperty(localName = "AirEquipType", isAttribute = true)
    private String airEquipType;

    public String getAirEquipType() {
        return airEquipType;
    }

    public void setAirEquipType(String airEquipType) {
        this.airEquipType = airEquipType;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "airEquipType='" + airEquipType + '\'' +
                '}';
    }
}
