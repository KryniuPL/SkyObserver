package com.skyobserver.repository;

import com.skyobserver.config.CacheConfiguration;
import com.skyobserver.http.HttpClient;
import com.skyobserver.model.Flight;
import com.skyobserver.model.MultiFlight;
import com.skyobserver.model.xml.FlightDetails;
import com.skyobserver.model.xml.OTA_AirDetailsRS;
import com.skyobserver.service.xml.FlightsDeserializer;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

import static com.skyobserver.config.ServerConfiguration.*;

@Component
public class MultiFlightsRepository {

    @Autowired
    private FlightsRepository flightsRepository;
    private HttpClient httpClient = new HttpClient();
    private FlightsDeserializer flightsDeserializer = new FlightsDeserializer();
    private static final Logger logger = LoggerFactory.getLogger(MultiFlightsRepository.class);
    private static final String REQUEST_SEPARATOR = "/";
    private final Cache<String, Collection<MultiFlight>> flightCache;

    public MultiFlightsRepository() {
        CacheManager cacheManager = CacheConfiguration.flightsCacheManager();
        this.flightCache = (Cache) cacheManager.getCache("cachedFlights", String.class, Collection.class);
    }

    public Collection<MultiFlight> searchForMultiFlights(String originAirportIATA, String destinationAirportIATA, String departureDate, String typeOfConnection, String currency) throws IOException {
        Collection<MultiFlight> flightList = flightCache.get(buildCacheKey(originAirportIATA, destinationAirportIATA, departureDate, typeOfConnection, currency));
        if(flightList == null){
            flightList = searchForMultiFlightsFromApi(originAirportIATA, destinationAirportIATA, departureDate, typeOfConnection, currency);
            flightCache.put(buildCacheKey(originAirportIATA, destinationAirportIATA, departureDate, typeOfConnection, currency), flightList);
        }
        return flightList;
    }

    public String buildCacheKey(String originAirportIATA, String destinationAirportIATA, String departureDate, String typeOfConnection, String currency){
        return originAirportIATA + "/" + destinationAirportIATA + "/" + departureDate + "/" + typeOfConnection + "/" + currency;
    }

    public List<MultiFlight> searchForMultiFlightsFromApi(String originAirportIATA, String destinationAirportIATA, String departureDate, String typeOfConnection, String currency) throws IOException {
        ResponseBody flightsDataXML = httpClient.doGet(buildRequestUrl(originAirportIATA, destinationAirportIATA, departureDate, typeOfConnection),
                Headers.of(Map.of(NAME_OF_FLIGHT_LOOKUP_SERVICE_HEADER, FLIGHT_LOOKUP_API_KEY)));
        OTA_AirDetailsRS deserializeFlights = flightsDeserializer.getDeserializedXML(flightsDataXML.string());
        List<FlightDetails> flightDetailsList = deserializeFlights.getFlightDetailsList();

        if(flightDetailsList == null){
            return Collections.emptyList();
        }

        List<MultiFlight> multiFlights = new ArrayList<>();
        for (FlightDetails flightDetail : flightDetailsList) {
            multiFlights.add(buildMultiFlightObject(flightDetail, currency));
        }
        return multiFlights;
    }

    public MultiFlight buildMultiFlightObject(FlightDetails flightDetails, String currency) throws IOException {
        List<Flight> flights = flightsRepository.buildFlightsList(flightDetails, currency);

        int totalMiles = Integer.parseInt(flightDetails.getTotalMiles());
        String journeyDuration = flightDetails.getTotalFlightTime();
        double totalJourneyPrice = calculateTotalFlightPrice(flights);
        String departureDate = flightDetails.getFlsDepartureDateTime();
        String arrivalDate = flightDetails.getFlsArrivalDateTime();
        return new MultiFlight(flightDetails.getFlsDepartureCode(), flightDetails.getFlsArrivalCode(), currency, departureDate, arrivalDate, totalMiles, journeyDuration, totalJourneyPrice, flights);
    }

    public double calculateTotalFlightPrice(List<Flight> flights) {
        return flights.stream()
                .mapToDouble(flight -> flight.getPrice().getValue())
                .sum();
    }

    public String buildRequestUrl(String originAirportIATA, String destinationAirportIATA, String departureDate, String typeOfConnection) {
        return FLIGHT_LOOKUP_HOST_URL + originAirportIATA + REQUEST_SEPARATOR + destinationAirportIATA + REQUEST_SEPARATOR + departureDate + "/?7Day=N&Connection=" + typeOfConnection + "&Compression=ALL&Sort=Departure&Time=ANY&Interline=Y&NoFilter=N&ExpandResults=Y&Max_Results=25";
    }
}
