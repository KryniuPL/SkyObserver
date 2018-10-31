package com.skyobserver.repository;

import com.skyobserver.http.HttpClient;
import com.skyobserver.model.Flight;
import com.skyobserver.model.xml.FlightDetails;
import com.skyobserver.model.xml.FlightLegDetails;
import com.skyobserver.model.xml.OTA_AirDetailsRS;
import com.skyobserver.service.xml.FlightsDeserializer;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.skyobserver.config.ServerConfiguration.FLIGHT_LOOKUP_API_KEY;
import static com.skyobserver.config.ServerConfiguration.FLIGHT_LOOKUP_HOST_URL;
import static com.skyobserver.config.ServerConfiguration.NAME_OF_FLIGHT_LOOKUP_SERVICE_HEADER;

public class FlightsRepository {

    private static final String REQUEST_SEPARATOR = "/";
    private HttpClient httpClient = new HttpClient();
    private FlightsDeserializer flightsDeserializer = new FlightsDeserializer();
    private AirportsRepository airportsRepository;


    public List<Flight> searchForFlights(String originAirportIATA, String destinationAirportIATA, String departureDate, String typeOfConnection) throws IOException {
        ResponseBody flightsDataXML = httpClient.doGet(buildRequestUrl(originAirportIATA, destinationAirportIATA, departureDate, typeOfConnection), Headers.of(Map.of(NAME_OF_FLIGHT_LOOKUP_SERVICE_HEADER, FLIGHT_LOOKUP_API_KEY)));
        OTA_AirDetailsRS deserializedFlights = flightsDeserializer.getDeserializedXML(flightsDataXML.string());
        List<FlightDetails> flightDetailsList = deserializedFlights.getFlightDetailsList();





        return null;
    }

    public static String buildRequestUrl(String originAirportIATA, String destinationAirportIATA, String departureDate, String typeOfConnection) {
        return FLIGHT_LOOKUP_HOST_URL + originAirportIATA + REQUEST_SEPARATOR + destinationAirportIATA + REQUEST_SEPARATOR + departureDate + "/?7Day=N&Connection=" + typeOfConnection + "&Compression=ALL&Sort=Departure&Time=ANY&Interline=N&NoFilter=N&ExpandResults=Y";
    }


    public Flight buildFlightObject(FlightDetails flightDetails){
        List<FlightLegDetails> flightLegDetailsList = flightDetails.getFlightLegDetailsList();

        if(flightLegDetailsList.size() == 1){
            FlightLegDetails directFlight = flightLegDetailsList.get(0);

            Flight dsa = new Flight.Builder()
                    .setDepartureTime(formatDateFromStringToLocalDateTime(directFlight.getDepartureDateTime()))
                    .setArrivalTime(formatDateFromStringToLocalDateTime(directFlight.getArrivalDateTime()))
                    .setOriginAirport(airportsRepository.findAirportByIataCode(directFlight.getDepartureAirport().getLocationCode()))
                    .setDestinationAirport(airportsRepository.findAirportByIataCode(directFlight.getArrivalAirport().getLocationCode()))
                    .build();


        }
        else if(flightLegDetailsList.size() > 1){
            //build flight object with stops
            //index will be from 1 to end
        }

     return null;
    }

    public LocalDateTime formatDateFromStringToLocalDateTime(String date) {
        return LocalDateTime.parse(date);
    }

    public Duration getDurationObjectFromStringExpression(String duration){
        char hours = duration.charAt(2)
        System.out.println("HOURS: " + hours);
        int minutes = Integer.parseInt(duration.substring(4,6));
        System.out.println("MINUTES : " + minutes);
        return Duration.ofHours(hours).plusMinutes(minutes);
    }

}
