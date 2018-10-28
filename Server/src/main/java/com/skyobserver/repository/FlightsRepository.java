package com.skyobserver.repository;

import com.skyobserver.http.HttpClient;
import com.skyobserver.model.Flight;
import com.skyobserver.model.xml.OTA_AirDetailsRS;
import com.skyobserver.service.xml.FlightsDeserializer;
import okhttp3.Headers;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FlightsRepository {

    public static final String REQUEST_SEPARATOR = "/";
    public static final String NAME_OF_FLIGHT_LOOKUP_SERVICE_HEADER = "Ocp-Apim-Subscription-Key";
    public static final String API_KEY = "***REMOVED***";
    private HttpClient httpClient = new HttpClient();
    private FlightsDeserializer flightsDeserializer = new FlightsDeserializer();
    private final AirportsRepository airportsRepository;
    private static String FLIGHT_LOOKUP_HOST_URL = "https://flightlookup.azure-api.net/v1/xml/TimeTable/";

    public FlightsRepository(AirportsRepository airportsRepository) {
        this.airportsRepository = airportsRepository;
    }

    public List<Flight> searchForFlights(String originAirportIATA, String destinationAirportIATA, String departureDate, String typeOfConnection) throws IOException {
        Response flightsDataXML = httpClient.doGet(buildRequestUrl(originAirportIATA, destinationAirportIATA, departureDate, typeOfConnection), Headers.of(Map.of(NAME_OF_FLIGHT_LOOKUP_SERVICE_HEADER, API_KEY)));
        OTA_AirDetailsRS deserializedFlights = flightsDeserializer.getDeserializedXML(flightsDataXML.toString());

        return null;
    }

    public static String buildRequestUrl(String originAirportIATA, String destinationAirportIATA, String departureDate, String typeOfConnection){
        return FLIGHT_LOOKUP_HOST_URL + originAirportIATA + REQUEST_SEPARATOR + destinationAirportIATA + REQUEST_SEPARATOR + departureDate + "/?7Day=N&Connection=" + typeOfConnection + "&Compression=ALL&Sort=Departure&Time=ANY&Interline=N&NoFilter=N&ExpandResults=Y";
    }
}
