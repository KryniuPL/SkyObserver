package com.skyobserver.repository;

import com.skyobserver.model.CachedPrice;
import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PricesRepositoryTest {


    private PricesRepository pricesRepository = new PricesRepository();

    @Test
    public void shouldReturnValidRequestURL() {
        String validURL = "https://skyscanner-skyscanner-flight-search-v1.p.mashape.com/apiservices/browsequotes/v1.0/US/PLN/en-US/WAW-sky/LHR-sky/2018-11-01/2018-11-02";
        assertEquals(validURL, pricesRepository.buildPriceRequestURL("PLN", "WAW", "LHR", "20181101", "20181102"));
    }

    @Test
    public void shouldReturnValidPriceObject() throws IOException {
        CachedPrice price = pricesRepository.getFlightPrice("PLN", "WAW", "LHR", "20190128", "20190130");
        assertNotNull(price);
        assertEquals(price.getCurrency(), "PLN");
        assertEquals(price.getOriginPointOfRoute(), "WAW");
        assertEquals(price.getDestinationPointOfRoute(), "LHR");
    }
}