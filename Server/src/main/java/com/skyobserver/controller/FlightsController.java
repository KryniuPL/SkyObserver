package com.skyobserver.controller;


import com.skyobserver.exceptions.AirportsNotFoundException;
import com.skyobserver.model.Flight;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightsController {

    @GetMapping("/flights/{from}/{to}/{date}/{connection}")
    public List<Flight> getAirportsStartingWithText(@PathVariable String expression) throws AirportsNotFoundException {
        return null;
    }


}
