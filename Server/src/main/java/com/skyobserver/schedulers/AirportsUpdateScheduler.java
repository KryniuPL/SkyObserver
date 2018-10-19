package com.skyobserver.schedulers;

import com.skyobserver.model.Airport;
import com.skyobserver.service.csv.airports.AirportsConverter;
import com.skyobserver.util.AirportsCSVSheetDownloader;
import com.skyobserver.util.AirportsCollectionsFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import static com.skyobserver.service.csv.airports.AirportsConverter.areNewAirportsAvailable;
import static com.skyobserver.service.csv.airports.AirportsConverter.getParserByFilePath;

@Component
public class AirportsUpdateScheduler {

    private static final String ACTUAL_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/airports.csv";
    private static final String NEW_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/new_airports.csv";
    private static final Logger logger = LoggerFactory.getLogger(AirportsUpdateScheduler.class);

    @Scheduled(cron = "*/100 * * * * *")
    public void scheduleUpdating() throws IOException {
        AirportsCSVSheetDownloader.downloadCSV();
        if (areNewAirportsAvailable()) {
            HashSet<Airport> actualAirportsFromCSV = AirportsConverter.getListOfObjects(getParserByFilePath(ACTUAL_AIRPORTS_CSV_FILE_PATH).getRecords());
            HashSet<Airport> newAirportsFromCSV = AirportsConverter.getListOfObjects(getParserByFilePath(NEW_AIRPORTS_CSV_FILE_PATH).getRecords());
            HashSet<Airport> newAirports = AirportsCollectionsFilter.getNewAirports(actualAirportsFromCSV, newAirportsFromCSV);

            List<Airport> filteredAirports = AirportsCollectionsFilter.filterListWithMediumAndLargeType(newAirports);
            //save filtered to database
            //get old airports
            //delete them from database
            //overwrite actual csv file
        } else {
            //do nothing
        }
        logger.info("Scheduler test");
    }


}
