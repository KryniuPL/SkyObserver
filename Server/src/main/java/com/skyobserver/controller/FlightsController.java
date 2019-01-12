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
import java.util.Collection;


@RestController
@RequestMapping("/flights")
public class FlightsController {

    @Autowired
    private MultiFlightsRepository multiFlightsRepository;
    private static final Logger logger = LoggerFactory.getLogger(PricesRepository.class);

    @GetMapping(value = "/{from}/{to}/{date}/{connection}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<MultiFlight> searchForFlights(@PathVariable String from, @PathVariable String to, @PathVariable String date, @PathVariable String connection, @PathVariable String currency) throws AirportsNotFoundException, IOException {
        Collection<MultiFlight> flights = multiFlightsRepository.searchForMultiFlights(from, to, date, connection, currency);
        if (flights.isEmpty()){
            throw new FlightsNotFoundException();
        }
        return flights;
    }
}
