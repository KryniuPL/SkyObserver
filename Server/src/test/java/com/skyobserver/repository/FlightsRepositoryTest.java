package com.skyobserver.repository;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlightsRepositoryTest {


    @Test
    public void shouldReturnValidRequestUrl(){
        String originAirportIATA = "WAW";
        String destinationAirportIATA = "LHR";
        String departureDate = "20181120";
        String typeOfConnection = "DIRECT";
        assertEquals(FlightsRepository.buildRequestUrl(originAirportIATA,destinationAirportIATA,departureDate,typeOfConnection), "https://flightlookup.azure-api.net/v1/xml/TimeTable/WAW/LHR/20181120/?7Day=N&Connection=DIRECT&Compression=ALL&Sort=Departure&Time=ANY&Interline=N&NoFilter=N&ExpandResults=Y");
    }
}