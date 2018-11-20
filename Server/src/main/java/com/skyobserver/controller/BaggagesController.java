package com.skyobserver.controller;

import com.skyobserver.model.Baggage;
import com.skyobserver.repository.BaggageRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/baggages")
public class BaggagesController {

    private BaggageRepository baggageRepository = new BaggageRepository();

    @GetMapping(value = "/{airlineName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Baggage getBaggageByAirlineName(@PathVariable String airlineName) throws IOException {
        return baggageRepository.getBaggageObjectByAirlineName(airlineName);
    }
}
