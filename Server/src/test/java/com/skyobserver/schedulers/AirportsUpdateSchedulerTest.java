package com.skyobserver.schedulers;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AirportsUpdateSchedulerTest {

    private static final Logger logger = LoggerFactory.getLogger(AirportsUpdateSchedulerTest.class);
    private static final String ACTUAL_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/airports.csv";
    private static final String NEW_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/new_airports.csv";

    @Test
    public void shouldShowNumberOfRowsInFiles() throws IOException {
        AirportsUpdateScheduler scheduler = new AirportsUpdateScheduler();
    }
}