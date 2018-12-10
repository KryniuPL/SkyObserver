package com.skyobserver.controller;

import com.skyobserver.exceptions.AirportsNotFoundException;
import com.skyobserver.exceptions.FlightsNotFoundException;
import com.skyobserver.model.MultiFlight;
import com.skyobserver.repository.MultiFlightsRepository;
import com.skyobserver.repository.PricesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightsController {

    @Autowired
    private MultiFlightsRepository multiFlightsRepository;
    private static final Logger logger = LoggerFactory.getLogger(PricesRepository.class);

    @GetMapping(value = "/{from}/{to}/{date}/{connection}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MultiFlight> searchForFlights(@PathVariable String from, @PathVariable String to, @PathVariable String date, @PathVariable String connection, @PathVariable String currency) throws AirportsNotFoundException, IOException {
        List<MultiFlight> flights = new ArrayList<>();
        try {
            flights = multiFlightsRepository.searchForMultiFlights(from, to, date, connection, currency);
        }
        catch (NullPointerException e){
            logger.error("NO DATA");
            logger.error("Origin Airport: " + from);
            logger.error("Destination Airport: " + to);
            logger.error("Date: " + date);
            logger.error("Connection Type: " + connection);
            logger.error("Currency: " + currency);
            e.printStackTrace();
        }
        return flights;
    }
}
