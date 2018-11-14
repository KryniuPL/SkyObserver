package com.skyobserver.repository;


import com.skyobserver.model.Airport;
import com.skyobserver.model.Flight;
import com.skyobserver.model.xml.FlightDetails;
import com.skyobserver.model.xml.FlightLegDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.skyobserver.config.ServerConfiguration.FLIGHT_LOOKUP_HOST_URL;

@Component
public class FlightsRepository {

    private static final String REQUEST_SEPARATOR = "/";
    @Autowired
    private AirportsRepository airportsRepository;
    private PricesRepository pricesRepository = new PricesRepository();
    private AirlineRepository airlineRepository = new AirlineRepository();
    private BaggageRepository baggageRepository = new BaggageRepository();

    public static String buildRequestUrl(String originAirportIATA, String destinationAirportIATA, String departureDate, String typeOfConnection) {
        return FLIGHT_LOOKUP_HOST_URL + originAirportIATA + REQUEST_SEPARATOR + destinationAirportIATA + REQUEST_SEPARATOR + departureDate + "/?7Day=N&Connection=" + typeOfConnection + "&Compression=ALL&Sort=Departure&Time=ANY&Interline=N&NoFilter=N&ExpandResults=Y&Max_Results=25";
    }

    public Flight buildDirectFlightObject(FlightLegDetails directFlight, String currency) throws IOException {
        Airport originAirport = airportsRepository.findAirportByIataCode(directFlight.getDepartureAirport().getLocationCode());
        Airport destinationAirport = airportsRepository.findAirportByIataCode(directFlight.getArrivalAirport().getLocationCode());

        return new Flight.Builder()
                .setDepartureTime(directFlight.getDepartureDateTime())
                .setArrivalTime(directFlight.getArrivalDateTime())
                .setOriginAirport(originAirport)
                .setDestinationAirport(destinationAirport)
                .setDuration(directFlight.getJourneyDuration())
                .setPrice(pricesRepository.getFlightPrice(currency, originAirport.getIataCode(), destinationAirport.getIataCode(), directFlight.getDepartureDateTime(), directFlight.getArrivalDateTime()))
                .setAirline(airlineRepository.getAirlineByCodeIataAirline(directFlight.getMarketingAirline().getCode()))
                .setBaggage(baggageRepository.getBaggageObjectByAirlineName(directFlight.getMarketingAirline().getCompanyShortName()))
                .build();
    }

    public List<Flight> buildFlightsList(FlightDetails flightDetails, String currency) throws IOException {
        List<FlightLegDetails> flightLegDetailsList = flightDetails.getFlightLegDetailsList();
        List<Flight> flights = new ArrayList<>();
        for (FlightLegDetails flightLegDetail : flightLegDetailsList) {
            flights.add(buildDirectFlightObject(flightLegDetail, currency));
        }
        return flights;
    }

}
