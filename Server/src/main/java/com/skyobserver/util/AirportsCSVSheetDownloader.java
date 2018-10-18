package com.skyobserver.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AirportsCSVSheetDownloader {

    private static final Logger logger = LoggerFactory.getLogger(AirportsCSVSheetDownloader.class);
    private static final String AIRPORTS_CSV_DOWNLOAD_URL = "http://ourairports.com/data/airports.csv";
    private static final File AIRPORTS_CSV_FILE = new File("src/main/resources/csv/new_airports.csv");

    public static void downloadCSV() {
        try {
            URL downloadURL = new URL(AIRPORTS_CSV_DOWNLOAD_URL);
            FileUtils.copyURLToFile(downloadURL, AIRPORTS_CSV_FILE);
            logger.info("Downloading airports csv");
        } catch (IOException e) {
            logger.error("Error downloading airports csv file");
        }
    }
}
