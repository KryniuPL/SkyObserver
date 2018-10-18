package com.skyobserver.schedulers;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AirportsUpdateSchedulerTest {

    @Test
    public void areNewAirportsAvailable() throws IOException {
        AirportsUpdateScheduler scheduler = new AirportsUpdateScheduler();
        assertTrue(scheduler.areNewAirportsAvailable());
    }
}