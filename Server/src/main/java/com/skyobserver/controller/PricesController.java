package com.skyobserver.controller;


import com.skyobserver.exceptions.PricesNotFoundException;
import com.skyobserver.model.CachedPrice;
import com.skyobserver.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/prices")
public class PricesController {


    private PricesRepository pricesRepository = new PricesRepository();

    @GetMapping(value = "/{currency}/{from}/{to}/{departureDate}/{returnDate}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CachedPrice getFlightPrice(@PathVariable String currency, @PathVariable String from, @PathVariable String to, @PathVariable String departureDate, @PathVariable String returnDate) throws PricesNotFoundException, IOException, InterruptedException {
        CachedPrice price = pricesRepository.getFlightPrice(currency, from, to, departureDate, returnDate);
        if (price==null){
            throw new PricesNotFoundException();
        }
        return price;
    }

}
