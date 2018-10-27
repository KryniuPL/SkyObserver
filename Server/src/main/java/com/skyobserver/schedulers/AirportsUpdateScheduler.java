package com.skyobserver.schedulers;

import com.skyobserver.model.Airport;
import com.skyobserver.repository.AirportsRepository;
import com.skyobserver.service.csv.AirportsConverter;
import com.skyobserver.util.AirportsCSVSheetDownloader;
import com.skyobserver.util.AirportsCollectionsFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import static com.skyobserver.config.ServerConfiguration.ACTUAL_AIRPORTS_CSV_FILE_PATH;
import static com.skyobserver.config.ServerConfiguration.CRON_SCHEDULE_EXPRESSION;
import static com.skyobserver.config.ServerConfiguration.NEW_AIRPORTS_CSV_FILE_PATH;
import static com.skyobserver.service.csv.AirportsConverter.areNewAirportsAvailable;
import static com.skyobserver.service.csv.AirportsConverter.getParserByFilePath;

@Component
public class AirportsUpdateScheduler {

    private final AirportsRepository airportsRepository;
    private static final Logger logger = LoggerFactory.getLogger(AirportsUpdateScheduler.class);


    public AirportsUpdateScheduler(AirportsRepository airportsRepository) {
        this.airportsRepository = airportsRepository;
    }

    @Scheduled(cron = CRON_SCHEDULE_EXPRESSION)
    public void scheduleUpdating() throws IOException {
        AirportsCSVSheetDownloader.downloadCSV();
        if (areNewAirportsAvailable()) {
            logger.info("Airports database update available");

            HashSet<Airport> actualAirportsFromCSV = AirportsConverter.getListOfObjects(getParserByFilePath(ACTUAL_AIRPORTS_CSV_FILE_PATH).getRecords());
            HashSet<Airport> newAirportsFromCSV = AirportsConverter.getListOfObjects(getParserByFilePath(NEW_AIRPORTS_CSV_FILE_PATH).getRecords());
            HashSet<Airport> newAirports = AirportsCollectionsFilter.getNewAirports(actualAirportsFromCSV, newAirportsFromCSV);
            List<Airport> filteredAirports = AirportsCollectionsFilter.filterListWithMediumAndLargeType(newAirports);

            logger.info("Saving new airports objects to database");
            airportsRepository.saveAll(filteredAirports);

            File oldAirportsFile = new File(ACTUAL_AIRPORTS_CSV_FILE_PATH);
            File newAirportsFile = new File(NEW_AIRPORTS_CSV_FILE_PATH);
            if (oldAirportsFile.delete()) {
                logger.info("Old Airports file deleted successfully");
                if (newAirportsFile.renameTo(oldAirportsFile))logger.info("Downloaded file replaced actual airports csv file");
                else logger.error("Failed to rename the file");
            } else logger.error("Failed to delete the file");
        } else logger.info("No update available");
    }
}
