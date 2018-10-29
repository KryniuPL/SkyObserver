package com.skyobserver.repository;

import com.skyobserver.model.Airline;
import org.springframework.web.client.RestTemplate;


public class AirlineRepository {

    private static final String AVIATION_EDGE_API_KEY = "***REMOVED***";
    private static final String AVIATION_EDGE_HOST_URL = "https://aviation-edge.com/v2/public/";
    private RestTemplate restTemplate = new RestTemplate();

    public Airline getAirlineByCodeIataAirline(String iataCodeAirline){
        return restTemplate.getForObject(buildAirlineRequestURL(iataCodeAirline), Airline.class);
    }

    public String buildAirlineRequestURL(String iataCodeAirline){
        return AVIATION_EDGE_HOST_URL + "airlineDatabase?key=" + AVIATION_EDGE_API_KEY + "&codeIataAirline=" + iataCodeAirline;
    }
}
