package com.skyobserver.schedulers;

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


@Component
public class AirportsUpdateScheduler {

    private static final String ACTUAL_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/airports.csv";
    private static final String NEW_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/new_airports.csv";
    private static final Logger logger = LoggerFactory.getLogger(AirportsUpdateScheduler.class);

    @Scheduled(cron = "*/100 * * * * *")
    public void scheduleUpdating() throws IOException {
        AirportsCSVSheetDownloader.downloadCSV();
        if (areNewAirportsAvailable()) {
            //get all new objects
            //filter them
            //save filtered to database
        } else {
            //do nothing
        }
        logger.info("Scheduler test");
    }

    private boolean areNewAirportsAvailable() {
        return getNumberOfRowsFromCSVFile(ACTUAL_AIRPORTS_CSV_FILE_PATH) != getNumberOfRowsFromCSVFile(NEW_AIRPORTS_CSV_FILE_PATH);
    }

    public int getNumberOfRowsFromCSVFile(String pathToCSVFile) {
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
