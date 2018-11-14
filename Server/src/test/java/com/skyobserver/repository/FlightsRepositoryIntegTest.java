package com.skyobserver.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.skyobserver.model.Flight;
import com.skyobserver.model.xml.FlightDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FlightsRepositoryIntegTest {

    @Autowired
    private FlightsRepository flightsRepository;
    private JacksonXmlModule xmlModule = new JacksonXmlModule();
    private String flightDetailsString = "<FlightDetails TotalFlightTime=\"PT2H45M\"\n" +
            "                   TotalMiles=\"914\"\n" +
            "                   TotalTripTime=\"PT2H45M\"\n" +
            "                   FLSDepartureDateTime=\"2018-11-15T20:05:00\"\n" +
            "                   FLSDepartureTimeOffset=\"+0100\"\n" +
            "                   FLSDepartureCode=\"WAW\"\n" +
            "                   FLSDepartureName=\"Warsaw\"\n" +
            "                   FLSArrivalDateTime=\"2018-11-15T21:50:00\"\n" +
            "                   FLSArrivalTimeOffset=\"+0000\"\n" +
            "                   FLSArrivalCode=\"LHR\"\n" +
            "                   FLSArrivalName=\"London Heathrow\"\n" +
            "                   FLSFlightType=\"NonStop\"\n" +
            "                   FLSFlightLegs=\"1\"\n" +
            "                   FLSFlightDays=\"...4...\"\n" +
            "                   FLSDayIndicator=\"\"\n" +
            "\n" +
            "    >\n" +
            "\n" +
            "\n" +
            "        <FlightLegDetails\n" +
            "                DepartureDateTime=\"2018-11-15T20:05:00\"\n" +
            "                FLSDepartureTimeOffset=\"+0100\"\n" +
            "                ArrivalDateTime=\"2018-11-15T21:50:00\"\n" +
            "                FLSArrivalTimeOffset=\"+0000\"\n" +
            "\n" +
            "                FlightNumber=\"285\"\n" +
            "                JourneyDuration=\"PT2H45M\"\n" +
            "                SequenceNumber=\"1\"\n" +
            "                LegDistance=\"914\"\n" +
            "                FLSMeals=\"RF\"\n" +
            "                FLSInflightServices=\"  9\"\n" +
            "                FLSUUID=\"WAWLHR20181115LO285\"\n" +
            "\n" +
            "\n" +
            "        >\n" +
            "            <DepartureAirport CodeContext=\"IATA\" LocationCode=\"WAW\" FLSLocationName=\"Warsaw\" Terminal=\" \"\n" +
            "                              FLSDayIndicator=\"\"/>\n" +
            "            <ArrivalAirport CodeContext=\"IATA\" LocationCode=\"LHR\" FLSLocationName=\"London Heathrow\" Terminal=\"2\"\n" +
            "                            FLSDayIndicator=\"\"/>\n" +
            "            <MarketingAirline Code=\"LO\" CodeContext=\"IATA\" CompanyShortName=\"LOT Polish Airlines\"/>\n" +
            "\n" +
            "            <Equipment AirEquipType=\"7M8\"/>\n" +
            "        </FlightLegDetails>\n" +
            "    </FlightDetails>";


    @Test
    public void shouldReturnValidRequestUrl() {
        String originAirportIATA = "WAW";
        String destinationAirportIATA = "LHR";
        String departureDate = "20181120";
        String typeOfConnection = "DIRECT";
        assertEquals(FlightsRepository.buildRequestUrl(originAirportIATA, destinationAirportIATA, departureDate, typeOfConnection), "https://flightlookup.azure-api.net/v1/xml/TimeTable/WAW/LHR/20181120/?7Day=N&Connection=DIRECT&Compression=ALL&Sort=Departure&Time=ANY&Interline=N&NoFilter=N&ExpandResults=Y");
    }

//    @Test
//    public void shouldReturnValidLocalDateTimeFromString() {
//        String date = "2018-11-15T07:35:00";
//        LocalDateTime formattedDate = flightsRepository.formatDateFromStringToLocalDateTime(date);
//        assertEquals(formattedDate.getDayOfMonth(), 15);
//        assertEquals(formattedDate.getMonth(), Month.NOVEMBER);
//        assertEquals(formattedDate.getYear(), 2018);
//        assertEquals(formattedDate.getHour(), 7);
//        assertEquals(formattedDate.getMinute(), 35);
//        assertEquals(formattedDate.getSecond(), 0);
//    }

//    @Test
//    public void shouldReturnValidDurationTime() {
//        Duration calculatedDuration = flightsRepository.getDurationObjectFromStringExpression("PT3H20M");
//        assertEquals(calculatedDuration.toHours(), 3);
//        assertEquals(calculatedDuration.toMinutes(), 200);
//    }

//    @Test
//    public void shouldReturnFlightObject() throws IOException, InterruptedException {
//        xmlModule.setDefaultUseWrapper(false);
//        ObjectMapper objectMapper = new XmlMapper(xmlModule);
//        FlightDetails flightDetails = objectMapper.readValue(flightDetailsString, FlightDetails.class);
//        Flight flight = flightsRepository.buildDirectFlightObject(flightDetails, "PLN");
//        System.out.println(flight.toString());
//        assertNotNull(flight);
//    }
}