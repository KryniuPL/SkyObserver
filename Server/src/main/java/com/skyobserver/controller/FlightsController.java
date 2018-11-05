package com.skyobserver.controller;


import com.skyobserver.exceptions.AirportsNotFoundException;
import com.skyobserver.model.Flight;
import com.skyobserver.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightsController {

    @Autowired
    private FlightsRepository flightsRepository;

    @GetMapping(value = "/{from}/{to}/{date}/{connection}/{currency}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Flight> searchForFlights(@PathVariable String from, @PathVariable String to, @PathVariable String date, @PathVariable String connection, @PathVariable String currency) throws AirportsNotFoundException, IOException, InterruptedException {
        if (connection.equals("DIRECT")){
            return flightsRepository.searchForDirectFlights(from,to, date, currency);
        }
        else {
            return null;
        }
    }


}
