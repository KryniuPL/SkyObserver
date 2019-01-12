package com.skyobserver.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.skyobserver.model.Flight;
import com.skyobserver.model.xml.FlightLegDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FlightsRepositoryIntegTest {

    @Autowired
    private FlightsRepository flightsRepository;
    private JacksonXmlModule xmlModule = new JacksonXmlModule();
    private String flightDetailsString = "<FlightLegDetails\n" +
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
            "        </FlightLegDetails>";

    @Test
    public void shouldReturnFlightObject() throws IOException {
        xmlModule.setDefaultUseWrapper(false);
        ObjectMapper objectMapper = new XmlMapper(xmlModule);
        FlightLegDetails flightDetails = objectMapper.readValue(flightDetailsString, FlightLegDetails.class);
        Flight flight = flightsRepository.buildDirectFlightObject(flightDetails, "PLN");
        assertNotNull(flight);
    }
}