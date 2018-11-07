package com.skyobserver.repository;

import com.skyobserver.model.Airport;
import com.skyobserver.schedulers.AirportsUpdateScheduler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AirportsRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(AirportsRepositoryTest.class);

    @Autowired
    private AirportsRepository airportsRepository;

    @Test
    public void shouldReturnNotNullObject(){
        Airport airport = airportsRepository.findAirportByIataCode("WAW");
        assertNotNull(airport);
        logger.info(airport.toString());
    }

    @Test
    public void shouldReturnNullObject(){
        Airport airport = airportsRepository.findAirportByIataCode("XCVX");
        assertNull(airport);
    }
}
