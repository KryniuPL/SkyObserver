package com.skyobserver.service.csv.airports;

import com.skyobserver.enums.AirportHeaders;
import com.skyobserver.model.Airport;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

public class AirportsConverter {

    private static final Logger logger = LoggerFactory.getLogger(AirportsConverter.class);
    private static final String ACTUAL_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/airports.csv";
    private static final String NEW_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/new_airports.csv";
    private static final int FIRST_RECORD_FROM_CSV = 1;

    public static HashSet<Airport> getListOfObjects(List<CSVRecord> records) {
        HashSet<Airport> convertedAirports = new HashSet<>();
        for (int i = FIRST_RECORD_FROM_CSV; i < records.size(); i++) {
            convertedAirports.add(getSingleObject(records.get(i)));
        }
        return convertedAirports;
    }

    public static CSVParser getParserByFilePath(String path) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(path));
        return CSVFormat.DEFAULT.withHeader(AirportHeaders.class).parse(reader);
    }

    public static boolean areNewAirportsAvailable() {
        return getNumberOfRowsFromCSVFile(ACTUAL_AIRPORTS_CSV_FILE_PATH) != getNumberOfRowsFromCSVFile(NEW_AIRPORTS_CSV_FILE_PATH);
    }

    private static int getNumberOfRowsFromCSVFile(String pathToCSVFile) {
        int numberOfRows = 0;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(pathToCSVFile));
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);
            numberOfRows = parser.getRecords().size();
        } catch (IOException e) {
            logger.error("Error" + e.getCause() + "reading csv file with path: " + pathToCSVFile);
        }
        return numberOfRows;
    }

    public static Airport getSingleObject(CSVRecord record) {
        long id = Long.parseLong(record.get("id"));
        String ident = record.get("ident");
        String type = record.get("type");
        String name = record.get("name");
        double latitudeDeg = Double.parseDouble(record.get("latitudeDeg"));
        double longitudeDeg = Double.parseDouble(record.get("longitudeDeg"));
        long elevationFt = 0l;
        if(record.get("elevationFt").equals("")){
            elevationFt = 0;
        }
        else {
         elevationFt = Long.parseLong(record.get("elevationFt"));
        }
        String continent = record.get("continent");
        String isoCountry = record.get("isoCountry");
        String isoRegion = record.get("isoRegion");
        String municipality = record.get("municipality");
        String scheduledService = record.get("scheduledService");
        String gpsCode = record.get("gpsCode");
        String iataCode = record.get("iataCode");
        String localCode = record.get("localCode");
        String homeLink = record.get("homeLink");
        String wikipediaLink = record.get("wikipediaLink");
        String keywords = record.get("keywords");
        return new Airport(id, ident, type, name, latitudeDeg, longitudeDeg, elevationFt, continent, isoCountry, isoRegion, municipality, scheduledService, gpsCode, iataCode, localCode, homeLink, wikipediaLink, keywords);
    }

}
