package com.skyobserver.repository;

import com.skyobserver.config.CacheConfiguration;
import com.skyobserver.http.HttpClient;
import com.skyobserver.model.Airline;
import com.skyobserver.service.json.AirlineParser;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import java.io.IOException;
import java.util.Map;

public class AirlineRepository {

    private static final String AVIATION_EDGE_API_KEY = "***REMOVED***";
    private static final String AVIATION_EDGE_HOST_URL = "https://aviation-edge.com/v2/public/";
    private AirlineParser airlineParser = new AirlineParser();
    private HttpClient httpClient = new HttpClient();
    private final Cache<String, Airline> airlineCache;

    public AirlineRepository() {
        CacheManager cacheManager = CacheConfiguration.airlinesCacheManager();
        this.airlineCache = cacheManager.getCache("cachedAirlines", String.class, Airline.class);
    }

    public Airline getAirlineByCodeIataAirline(String iataCodeAirline) throws IOException {
        Airline airline = airlineCache.get(iataCodeAirline);
        if (airline == null){
            airline = getAirlineFromApi(iataCodeAirline);
            airlineCache.put(iataCodeAirline, airline);
        }
        return airline;
    }

    public Airline getAirlineFromApi(String iataAirlineCode) throws IOException {
        ResponseBody responseFromAPI = httpClient.doGet(buildAirlineRequestURL(iataAirlineCode), Headers.of(Map.of()));
        return airlineParser.getAirlineObjectFromJSONResponse(responseFromAPI.string());
    }

    public String buildAirlineRequestURL(String iataCodeAirline) {
        return AVIATION_EDGE_HOST_URL + "airlineDatabase?key=" + AVIATION_EDGE_API_KEY + "&codeIataAirline=" + iataCodeAirline;
    }
}
