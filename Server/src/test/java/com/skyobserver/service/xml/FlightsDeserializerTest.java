package com.skyobserver.service.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.skyobserver.model.xml.FlightDetails;
import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.*;

public class FlightsDeserializerTest {

    JacksonXmlModule xmlModule = new JacksonXmlModule();

    private String flightLegDetailsXmlObject= "<FlightDetails TotalFlightTime=\"PT3H35M\"\n" +
            "                   TotalMiles=\"931\"\n" +
            "                   TotalTripTime=\"PT4H25M\"\n" +
            "                   FLSDepartureDateTime=\"2018-11-15T06:40:00\"\n" +
            "                   FLSDepartureTimeOffset=\"+0100\"\n" +
            "                   FLSDepartureCode=\"WAW\"\n" +
            "                   FLSDepartureName=\"Warsaw\"\n" +
            "                   FLSArrivalDateTime=\"2018-11-15T10:05:00\"\n" +
            "                   FLSArrivalTimeOffset=\"+0000\"\n" +
            "                   FLSArrivalCode=\"LHR\"\n" +
            "                   FLSArrivalName=\"London Heathrow\"\n" +
            "                   FLSFlightType=\"Connect\"\n" +
            "                   FLSFlightLegs=\"2\"\n" +
            "                   FLSFlightDays=\"...4...\"\n" +
            "                   FLSDayIndicator=\"\"\n" +
            "\n" +
            "    >\n" +
            "\n" +
            "\n" +
            "        <FlightLegDetails\n" +
            "                DepartureDateTime=\"2018-11-15T06:40:00\"\n" +
            "                FLSDepartureTimeOffset=\"+0100\"\n" +
            "                ArrivalDateTime=\"2018-11-15T08:50:00\"\n" +
            "                FLSArrivalTimeOffset=\"+0100\"\n" +
            "                FlightNumber=\"2560\"\n" +
            "                JourneyDuration=\"PT2H10M\"\n" +
            "                SequenceNumber=\"1\"\n" +
            "                LegDistance=\"713\"\n" +
            "                FLSMeals=\"B\"\n" +
            "                FLSInflightServices=\"  9\"\n" +
            "                FLSUUID=\"WAWBRU20181115SN2560\"\n" +
            "        >\n" +
            "            <DepartureAirport CodeContext=\"IATA\" LocationCode=\"WAW\" FLSLocationName=\"Warsaw\" Terminal=\" \" FLSDayIndicator=\"\" />\n" +
            "            <ArrivalAirport CodeContext=\"IATA\" LocationCode=\"BRU\" FLSLocationName=\"Brussels\" Terminal=\" \" FLSDayIndicator=\"\" />\n" +
            "            <MarketingAirline Code=\"SN\" CodeContext=\"IATA\" CompanyShortName=\"Brussels Airlines\" />\n" +
            "\n" +
            "            <Equipment AirEquipType=\"319\" />\n" +
            "        </FlightLegDetails>\n" +
            "        <FlightLegDetails\n" +
            "                DepartureDateTime=\"2018-11-15T09:40:00\"\n" +
            "                FLSDepartureTimeOffset=\"+0100\"\n" +
            "                ArrivalDateTime=\"2018-11-15T10:05:00\"\n" +
            "                FLSArrivalTimeOffset=\"+0000\"\n" +
            "\n" +
            "                FlightNumber=\"2093\"\n" +
            "                JourneyDuration=\"PT1H25M\"\n" +
            "                SequenceNumber=\"2\"\n" +
            "                LegDistance=\"218\"\n" +
            "                FLSMeals=\"S\"\n" +
            "                FLSInflightServices=\"  9\"\n" +
            "                FLSUUID=\"BRULHR20181115SN2093\"\n" +
            "\n" +
            "\n" +
            "        >\n" +
            "            <DepartureAirport CodeContext=\"IATA\" LocationCode=\"BRU\" FLSLocationName=\"Brussels\" Terminal=\" \" FLSDayIndicator=\"\" />\n" +
            "            <ArrivalAirport CodeContext=\"IATA\" LocationCode=\"LHR\" FLSLocationName=\"London Heathrow\" Terminal=\"2\" FLSDayIndicator=\"\" />\n" +
            "            <MarketingAirline Code=\"SN\" CodeContext=\"IATA\" CompanyShortName=\"Brussels Airlines\" />\n" +
            "\n" +
            "            <Equipment AirEquipType=\"320\" />\n" +
            "        </FlightLegDetails>\n" +
            "    </FlightDetails>";

    @Test
    public void shouldConvertXmlNodeToObject() throws IOException {
        xmlModule.setDefaultUseWrapper(false);
        ObjectMapper objectMapper = new XmlMapper(xmlModule);
        FlightDetails flighDetails = objectMapper.readValue(flightLegDetailsXmlObject, FlightDetails.class);
        System.out.println(flighDetails.toString());
        assertNotNull(flightLegDetailsXmlObject);
    }
}