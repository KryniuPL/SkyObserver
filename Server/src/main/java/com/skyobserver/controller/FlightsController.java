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

    @GetMapping("/flights/{from}/{to}/{date}/{connection}/{currency}")
    public List<Flight> getAirportsStartingWithText(@PathVariable String from, @PathVariable String to, @PathVariable String date, @PathVariable String connection, @PathVariable String currency) throws AirportsNotFoundException {
        return null;
    }


}
