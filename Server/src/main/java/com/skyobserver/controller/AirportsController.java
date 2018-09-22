package com.skyobserver.controller;

import com.skyobserver.exceptions.AirportsNotFoundException;
import com.skyobserver.model.Airport;
import com.skyobserver.repository.AirportsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportsController {

    private final AirportsRepository airportsRepository;

    public AirportsController(AirportsRepository airportsRepository) {
        this.airportsRepository = airportsRepository;
    }

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportsRepository.findAll();
    }

    @GetMapping("/getAirportsStartingWith/{expression}")
    public List<Airport> getAirportsStartingWithText(@PathVariable String expression) throws AirportsNotFoundException {
        List<Airport> airports = airportsRepository.findAirportsByMunicipalityStartingWith(expression);
        if (airports.isEmpty()){
            throw new AirportsNotFoundException();
        }
        else return airports;
    }
}
