package com.skyobserver.repository;

import com.skyobserver.http.HttpClient;
import com.skyobserver.model.Flight;
import com.skyobserver.model.MultiFlight;
import com.skyobserver.model.xml.FlightDetails;
import com.skyobserver.model.xml.OTA_AirDetailsRS;
import com.skyobserver.service.xml.FlightsDeserializer;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static com.skyobserver.config.ServerConfiguration.FLIGHT_LOOKUP_API_KEY;
import static com.skyobserver.config.ServerConfiguration.NAME_OF_FLIGHT_LOOKUP_SERVICE_HEADER;
import static com.skyobserver.repository.FlightsRepository.buildRequestUrl;

@Component
public class MultiFlightsRepository {

    @Autowired
    private FlightsRepository flightsRepository;
    private HttpClient httpClient = new HttpClient();
    private FlightsDeserializer flightsDeserializer = new FlightsDeserializer();
    private static final Logger logger = LoggerFactory.getLogger(MultiFlightsRepository.class);

    public List<MultiFlight> searchForMultiFlights(String originAirportIATA, String destinationAirportIATA, String departureDate,String typeOfConnection, String currency) throws IOException {
        ResponseBody flightsDataXML = httpClient.doGet(buildRequestUrl(originAirportIATA, destinationAirportIATA, departureDate, typeOfConnection),
                Headers.of(Map.of(NAME_OF_FLIGHT_LOOKUP_SERVICE_HEADER, FLIGHT_LOOKUP_API_KEY)));
        OTA_AirDetailsRS deserializeFlights = flightsDeserializer.getDeserializedXML(flightsDataXML.string());
        List<FlightDetails> flightDetailsList = deserializeFlights.getFlightDetailsList();

        List<MultiFlight> multiFlights = new ArrayList<>();
        try{
            for (FlightDetails flightDetail: flightDetailsList){
                multiFlights.add(buildMultiFlightObject(flightDetail, currency));
            }
        }
        catch (Exception e){
            logErrorResponse(flightsDataXML.string());
            multiFlights = Collections.emptyList();
        }
        return multiFlights;
    }

    private void logErrorResponse(String badResponse) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        String path = "src/main/resources/logger/" + "apiResponse" + "_" + now.getHour() + ":" + now.getMinute();
        FileUtils.writeStringToFile(new File(path), badResponse, "UTF-8");
    }

    public MultiFlight buildMultiFlightObject(FlightDetails flightDetails, String currency) throws IOException {
        List<Flight> flights = flightsRepository.buildFlightsList(flightDetails,currency);

        int totalMiles = Integer.parseInt(flightDetails.getTotalMiles());
        String journeyDuration = flightDetails.getTotalFlightTime();
        int totalJourneyPrice = calculateTotalFlightPrice(flights);
        String departureDate = flightDetails.getFlsDepartureDateTime();
        String arrivalDate = flightDetails.getFlsArrivalDateTime();
        return new MultiFlight(flightDetails.getFlsDepartureCode(),flightDetails.getFlsArrivalCode(),currency, departureDate, arrivalDate, totalMiles, journeyDuration, totalJourneyPrice, flights);
    }

    public int calculateTotalFlightPrice(List<Flight> flights){
        return flights.stream()
                .mapToInt(flight -> flight.getPrice().get("value").asInt())
                .sum();
    }

}
