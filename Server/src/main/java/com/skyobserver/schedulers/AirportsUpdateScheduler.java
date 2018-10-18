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
    private static final Logger log = LoggerFactory.getLogger(AirportsUpdateScheduler.class);

    @Scheduled(cron = "*/100 * * * * *")
    public void scheduleUpdating() throws IOException {
        AirportsCSVSheetDownloader.downloadCSV();
        if(areNewAirportsAvailable()){
            //get all new objects
            //filter them
            //save filtered to database
        }
        else {
            //do nothing
        }
        log.info("Scheduler test");
    }

        public boolean areNewAirportsAvailable() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(ACTUAL_AIRPORTS_CSV_FILE_PATH));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        int numberOfAirportsFromActualCSV = csvParser.getRecords().size();

        Reader secondReader = Files.newBufferedReader(Paths.get(NEW_AIRPORTS_CSV_FILE_PATH));
        CSVParser secondCSVParser = new CSVParser(secondReader, CSVFormat.DEFAULT);
        int numberOfAirportsFromNewCSV = secondCSVParser.getRecords().size();

        if(numberOfAirportsFromActualCSV == numberOfAirportsFromNewCSV) return false;
        else return true;
    }




}
