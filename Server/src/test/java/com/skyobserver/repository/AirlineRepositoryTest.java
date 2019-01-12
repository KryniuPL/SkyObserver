package com.skyobserver.repository;

import com.skyobserver.model.Airline;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AirlineRepositoryTest {

    private AirlineRepository airlineRepository = new AirlineRepository();

    @Test
    public void shouldReturnValidAirlineRequestURL(){
        String validURL = "https://aviation-edge.com/v2/public/airlineDatabase?key=***REMOVED***&codeIataAirline=AA";
        assertEquals(airlineRepository.buildAirlineRequestURL("AA"), validURL);
    }

    @Test
    public void shouldReturnAirlineObjectFromAPI() throws IOException {
        Airline airline = airlineRepository.getAirlineByCodeIataAirline("AA");
        System.out.println(airline.toString());
        assertNotNull(airline);
    }

    @Test
    public void shouldReturnProprtAirlineObjectFromAPI() throws IOException {
        Airline airline = airlineRepository.getAirlineFromApi("AA");
        assertEquals(airline.getNameAirline(), "American Airlines");
        assertEquals(airline.getStatusAirline(), "active");
        assertEquals(airline.getCallsign(), "AMERICAN");
        assertEquals(airline.getNameCountry(), "United States");
    }
}