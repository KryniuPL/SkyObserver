package com.skyobserver.schedulers;

import com.skyobserver.enums.AirportHeaders;
import com.skyobserver.model.Airport;
import com.skyobserver.service.csv.airports.AirportsConverter;
import com.skyobserver.util.AirportsCSVSheetDownloader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Component
public class AirportsUpdateScheduler {

    private static final String ACTUAL_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/airports.csv";
    private static final String NEW_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/new_airports.csv";
    private static final Logger logger = LoggerFactory.getLogger(AirportsUpdateScheduler.class);

    @Scheduled(cron = "*/100 * * * * *")
    public void scheduleUpdating() throws IOException {
        AirportsCSVSheetDownloader.downloadCSV();
        if (areNewAirportsAvailable()) {
            Reader reader = Files.newBufferedReader(Paths.get(NEW_AIRPORTS_CSV_FILE_PATH));
            CSVParser parser = CSVFormat.DEFAULT.withHeader(AirportHeaders.class).parse(reader);
            List<Airport> airports = AirportsConverter.getListOfAirportsInTheRangeOf(getNumberOfRowsFromCSVFile(ACTUAL_AIRPORTS_CSV_FILE_PATH) +1, getNumberOfRowsFromCSVFile(NEW_AIRPORTS_CSV_FILE_PATH), parser.getRecords());
            //filter them
            //save filtered to database
        } else {
            //do nothing
        }
        logger.info("Scheduler test");
    }

    public static boolean areNewAirportsAvailable() {
        return getNumberOfRowsFromCSVFile(ACTUAL_AIRPORTS_CSV_FILE_PATH) != getNumberOfRowsFromCSVFile(NEW_AIRPORTS_CSV_FILE_PATH);
    }

    public static int getNumberOfRowsFromCSVFile(String pathToCSVFile) {
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



}
