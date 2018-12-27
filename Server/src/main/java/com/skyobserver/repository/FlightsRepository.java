package com.skyobserver.repository;


import com.skyobserver.model.Airport;
import com.skyobserver.model.CachedPrice;
import com.skyobserver.model.Flight;
import com.skyobserver.model.xml.FlightDetails;
import com.skyobserver.model.xml.FlightLegDetails;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FlightsRepository {

    @Autowired
    private AirportsRepository airportsRepository;
    private PricesRepository pricesRepository = new PricesRepository();
    private AirlineRepository airlineRepository = new AirlineRepository();
    private BaggageRepository baggageRepository = new BaggageRepository();
    private final Cache<String, CachedPrice> priceCache;

    @Autowired
    public FlightsRepository(CacheManager cacheManager) {
        this.priceCache = cacheManager.getCache("cachedFlightPrices", String.class, CachedPrice.class);
    }

    public Flight buildDirectFlightObject(FlightLegDetails directFlight, String currency) throws IOException {
        Airport originAirport = airportsRepository.findAirportByIataCode(directFlight.getDepartureAirport().getLocationCode());
        Airport destinationAirport = airportsRepository.findAirportByIataCode(directFlight.getArrivalAirport().getLocationCode());
        CachedPrice cachedPrice = priceCache.get(originAirport.getIataCode() + "-" + destinationAirport.getIataCode());

        if (cachedPrice == null){
            cachedPrice = pricesRepository.getFlightPrice(currency, originAirport.getIataCode(), destinationAirport.getIataCode(), directFlight.getDepartureDateTime(), directFlight.getArrivalDateTime());
            priceCache.put(cachedPrice.getOriginPointOfRoute() + "-" + cachedPrice.getDestinationPointOfRoute(), cachedPrice);
        }
        else System.out.println("found in cache");

        return new Flight.Builder()
                .setDepartureTime(directFlight.getDepartureDateTime())
                .setArrivalTime(directFlight.getArrivalDateTime())
                .setOriginAirport(originAirport)
                .setDestinationAirport(destinationAirport)
                .setDuration(directFlight.getJourneyDuration())
                .setPrice(cachedPrice)
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
