package com.skyobserver.util;

import com.skyobserver.model.Airport;
import com.skyobserver.service.csv.airports.AirportsConverter;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import static org.junit.Assert.*;
import static com.skyobserver.service.csv.airports.AirportsConverter.getParserByFilePath;

public class AirportsCollectionsFilterTest {

    private static final String ACTUAL_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/airports.csv";
    private static final String NEW_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/new_airports.csv";

    @Test
    public void shouldReturnOnlyMediumOrLargeAirports() throws IOException {

    }

    @Test
    public void shouldReturnNewAirports() throws IOException {
        HashSet<Airport> actualAirportsFromCSV = AirportsConverter.getListOfObjects(getParserByFilePath(ACTUAL_AIRPORTS_CSV_FILE_PATH).getRecords());
        HashSet<Airport> newAirportsFromCSV = AirportsConverter.getListOfObjects(getParserByFilePath(NEW_AIRPORTS_CSV_FILE_PATH).getRecords());
        HashSet<Airport> newAirports = AirportsCollectionsFilter.getNewAirports(actualAirportsFromCSV, newAirportsFromCSV);
        List<Airport> filteredAirports = AirportsCollectionsFilter.filterListWithMediumAndLargeType(newAirports);

        for (Airport filteredAirport : filteredAirports){
            assertTrue(filteredAirport.getType().equals("medium_airport") || filteredAirport.getType().equals("large_airport"));
            System.out.println(filteredAirport.toString());
        }
    }

    @Test
    public void shouldReturnOldAirports() throws IOException {
        HashSet<Airport> actualAirportsFromCSV = AirportsConverter.getListOfObjects(getParserByFilePath(ACTUAL_AIRPORTS_CSV_FILE_PATH).getRecords());
        HashSet<Airport> newAirportsFromCSV = AirportsConverter.getListOfObjects(getParserByFilePath(NEW_AIRPORTS_CSV_FILE_PATH).getRecords());
        HashSet<Airport> oldAirports = AirportsCollectionsFilter.getOldAirports(actualAirportsFromCSV, newAirportsFromCSV);

        for(Airport oldAirport : oldAirports){
            assertNotNull(oldAirport);
            System.out.println(oldAirport.toString());
        }
    }
}