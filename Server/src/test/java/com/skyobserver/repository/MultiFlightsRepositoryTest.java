package com.skyobserver.repository;

import com.skyobserver.model.CachedPrice;
import com.skyobserver.model.Flight;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MultiFlightsRepositoryTest {

    private MultiFlightsRepository multiFlightsRepository = new MultiFlightsRepository();


    @Test
    public void shouldReturnValidCacheKey() {
        assertEquals("WAW/LHR/20190110/DIRECT/PLN",
                multiFlightsRepository.buildCacheKey("WAW", "LHR", "20190110", "DIRECT", "PLN"));
    }

    @Test
    public void shouldReturnProperSumOfFlightsPrices() {
        Flight firstFlight = new Flight.Builder()
                .setPrice(new CachedPrice(200.43))
                .build();

        Flight secondFlight = new Flight.Builder()
                .setPrice(new CachedPrice(800.99))
                .build();

        Flight thirdFlight = new Flight.Builder()
                .setPrice(new CachedPrice(436.67))
                .build();
        List<Flight> flightList = List.of(firstFlight, secondFlight, thirdFlight);
        assertEquals(1438.09, multiFlightsRepository.calculateTotalFlightPrice(flightList), 0.001);
    }

    @Test
    public void shouldReturnValidRequestUrl() {
        assertEquals("https://flightlookup.azure-api.net/v1/xml/TimeTable/WAW/LHR/20190129/?7Day=N&Connection=DIRECT&Compression=ALL&Sort=Departure&Time=ANY&Interline=Y&NoFilter=N&ExpandResults=Y&Max_Results=25",
                multiFlightsRepository.buildRequestUrl("WAW", "LHR", "20190129", "DIRECT"));
    }
}