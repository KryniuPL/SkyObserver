package com.skyobserver.service;

import com.skyobserver.enums.AirportCSVFileHeaders;
import com.skyobserver.model.Airport;
import com.skyobserver.service.csv.AirportsConverter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class AirportsConverterTest {

    private static final String ACTUAL_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/airports.csv";
    private AirportsConverter converter = new AirportsConverter();

    @Test
    public void shouldReturnNotNullAirportObject() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(ACTUAL_AIRPORTS_CSV_FILE_PATH));
        CSVParser parser = CSVFormat.DEFAULT.withHeader(AirportCSVFileHeaders.class).parse(reader);
        List<CSVRecord> recordList = parser.getRecords();
        Airport airport = converter.getSingleObject(recordList.get(1));
        assertNotNull(airport);
    }

    @Test
    public void shouldReturnNewAirports() throws IOException {
        CSVParser parser = AirportsConverter.getParserByFilePath(ACTUAL_AIRPORTS_CSV_FILE_PATH);
        HashSet<Airport> airportHashSet = AirportsConverter.getListOfObjects(parser.getRecords());
        assertNotNull(airportHashSet);
    }


}