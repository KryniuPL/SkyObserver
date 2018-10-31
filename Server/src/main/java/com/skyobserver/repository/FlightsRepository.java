package com.skyobserver.repository;

import com.skyobserver.http.HttpClient;
import com.skyobserver.model.Airport;
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

import static com.skyobserver.config.ServerConfiguration.*;

public class FlightsRepository {

    private static final String REQUEST_SEPARATOR = "/";
    private HttpClient httpClient = new HttpClient();
    private FlightsDeserializer flightsDeserializer = new FlightsDeserializer();

    @Autowired
    private AirportsRepository airportsRepository;
    private PricesRepository pricesRepository = new PricesRepository();
    private AirlineRepository airlineRepository = new AirlineRepository();
    private BaggageRepository baggageRepository = new BaggageRepository();

    public List<Flight> searchForFlights(String originAirportIATA, String destinationAirportIATA, String departureDate, String typeOfConnection) throws IOException {
        ResponseBody flightsDataXML = httpClient.doGet(buildRequestUrl(originAirportIATA, destinationAirportIATA, departureDate, typeOfConnection), Headers.of(Map.of(NAME_OF_FLIGHT_LOOKUP_SERVICE_HEADER, FLIGHT_LOOKUP_API_KEY)));
        OTA_AirDetailsRS deserializedFlights = flightsDeserializer.getDeserializedXML(flightsDataXML.string());
        List<FlightDetails> flightDetailsList = deserializedFlights.getFlightDetailsList();


        return null;
    }

    public static String buildRequestUrl(String originAirportIATA, String destinationAirportIATA, String departureDate, String typeOfConnection) {
        return FLIGHT_LOOKUP_HOST_URL + originAirportIATA + REQUEST_SEPARATOR + destinationAirportIATA + REQUEST_SEPARATOR + departureDate + "/?7Day=N&Connection=" + typeOfConnection + "&Compression=ALL&Sort=Departure&Time=ANY&Interline=N&NoFilter=N&ExpandResults=Y";
    }


    public Flight buildDirectFlightObject(FlightDetails flightDetails, String currency) throws IOException {
        FlightLegDetails directFlight = flightDetails.getFlightLegDetailsList().get(0);
        Airport originAirport = airportsRepository.findAirportByIataCode(directFlight.getDepartureAirport().getLocationCode());
        Airport destinationAirport = airportsRepository.findAirportByIataCode(directFlight.getArrivalAirport().getLocationCode());

        return new Flight.Builder()
                .setDepartureTime(formatDateFromStringToLocalDateTime(directFlight.getDepartureDateTime()))
                .setArrivalTime(formatDateFromStringToLocalDateTime(directFlight.getArrivalDateTime()))
                .setOriginAirport(originAirport)
                .setDestinationAirport(destinationAirport)
                .setDuration(getDurationObjectFromStringExpression(directFlight.getJourneyDuration()))
                .setPrice(pricesRepository.getFlightPrice(currency, originAirport.getIataCode(), destinationAirport.getIataCode(), formatDateWithDashes(directFlight.getDepartureDateTime()), formatDateWithDashes(directFlight.getArrivalDateTime())))
                .setAirline(airlineRepository.getAirlineByCodeIataAirline(directFlight.getMarketingAirline().getCode()))
                .setBaggage(baggageRepository.getBaggageObjectByAirlineName(directFlight.getMarketingAirline().getCode()))
                .build();
    }

    public String formatDateWithDashes(String date) {
        String[] strings = date.split("T");
        return strings[0];
    }

    public LocalDateTime formatDateFromStringToLocalDateTime(String date) {
        return LocalDateTime.parse(date);
    }

    public Duration getDurationObjectFromStringExpression(String duration) {
        return Duration.parse(duration);
    }

}
