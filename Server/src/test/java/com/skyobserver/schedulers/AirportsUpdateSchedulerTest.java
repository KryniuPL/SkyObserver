package com.skyobserver.schedulers;

import org.junit.Test;

import java.io.IOException;

public class AirportsUpdateSchedulerTest {


    private static final String ACTUAL_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/airports.csv";
    private static final String NEW_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/new_airports.csv";

    @Test
    public void shouldShowNumberOfRowsInFiles() throws IOException {
        AirportsUpdateScheduler scheduler = new AirportsUpdateScheduler();
        System.out.println(scheduler.getNumberOfRowsFromCSVFile(ACTUAL_AIRPORTS_CSV_FILE_PATH));
        System.out.println(scheduler.getNumberOfRowsFromCSVFile(NEW_AIRPORTS_CSV_FILE_PATH));
    }
}