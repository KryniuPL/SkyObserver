package com.skyobserver.controller;

import com.skyobserver.exceptions.AirportsNotFoundException;
import com.skyobserver.model.Airport;
import com.skyobserver.repository.AirportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportsController {

    @Autowired
    private AirportsRepository airportsRepository;

    @GetMapping("/getAirportsStartingWith/{expression}")
    public List<Airport> getAirportsStartingWithText(@PathVariable String expression) throws AirportsNotFoundException {
        List<Airport> airports = airportsRepository.findAirportsByMunicipalityStartingWith(expression);
        if (airports.isEmpty()){
            throw new AirportsNotFoundException();
        }
        else return airports;
    }

    @GetMapping(value = "/{iataCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Airport getAirportByName(@PathVariable String iataCode){
        return airportsRepository.findAirportByIataCode(iataCode);
    }
}
