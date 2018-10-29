package com.skyobserver.repository;

import org.junit.Test;

import static org.junit.Assert.*;

public class AirlineRepositoryTest {

    private AirlineRepository airlineRepository = new AirlineRepository();

    @Test
    public void shouldReturnValidAirlineRequestURL(){
        String validURL = "https://aviation-edge.com/v2/public/airlineDatabase?key=***REMOVED***&codeIataAirline=AA";
        assertEquals(airlineRepository.buildAirlineRequestURL("AA"), validURL);
    }


}