package com.skyobserver.util;

import com.skyobserver.enums.AirportHeaders;
import com.skyobserver.model.Airport;
import com.skyobserver.schedulers.AirportsUpdateScheduler;
import com.skyobserver.service.csv.airports.AirportsConverter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class AirportsCollectionsFilterTest {

    private static final Logger logger = LoggerFactory.getLogger(AirportsCollectionsFilterTest.class);
    private static final String ACTUAL_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/airports.csv";
    private static final String NEW_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/new_airports.csv";

    @Test
    public void shouldReturnOnlyMediumOrLargeAirports() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(NEW_AIRPORTS_CSV_FILE_PATH));
        CSVParser parser = CSVFormat.DEFAULT.withHeader(AirportHeaders.class).parse(reader);
        int numberOfActualAirports = AirportsUpdateScheduler.getNumberOfRowsFromCSVFile(ACTUAL_AIRPORTS_CSV_FILE_PATH);
        int numberOfNewAirports = AirportsUpdateScheduler.getNumberOfRowsFromCSVFile(NEW_AIRPORTS_CSV_FILE_PATH);
        logger.info("Actual airports: " + numberOfActualAirports);
        logger.info("New airports: " + numberOfNewAirports);

        List<Airport> airports = AirportsConverter.getListOfAirportsInTheRangeOf(numberOfActualAirports +1, numberOfNewAirports, parser.getRecords());
        List<Airport> filteredAirports = AirportsCollectionsFilter.filterListWithMediumAndLargeType(airports);

        for(Airport filteredAirport: filteredAirports){
            assertTrue(filteredAirport.getType().equals("medium_airports") || filteredAirport.getType().equals("large_airport"));
        }
    }
}