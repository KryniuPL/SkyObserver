package com.skyobserver.repository;

import com.skyobserver.http.HttpClient;
import com.skyobserver.model.Airline;
import com.skyobserver.service.json.AirlineParser;
import okhttp3.Headers;
import okhttp3.ResponseBody;


import java.io.IOException;
import java.util.Map;


public class AirlineRepository {

    private static final String AVIATION_EDGE_API_KEY = "***REMOVED***";
    private static final String AVIATION_EDGE_HOST_URL = "https://aviation-edge.com/v2/public/";
    private AirlineParser airlineParser = new AirlineParser();
    private HttpClient httpClient = new HttpClient();

    public Airline getAirlineByCodeIataAirline(String iataCodeAirline) throws IOException {
        String responseFromAPI = httpClient.doGet(buildAirlineRequestURL(iataCodeAirline), Headers.of(Map.of()));
        return airlineParser.getAirlineObjectFromJSONResponse(responseFromAPI);
    }

    public String buildAirlineRequestURL(String iataCodeAirline){
        return AVIATION_EDGE_HOST_URL + "airlineDatabase?key=" + AVIATION_EDGE_API_KEY + "&codeIataAirline=" + iataCodeAirline;
    }
}
