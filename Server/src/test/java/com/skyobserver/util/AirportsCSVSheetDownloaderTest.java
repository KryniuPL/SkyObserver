package com.skyobserver.util;

import org.junit.Test;

public class AirportsCSVSheetDownloaderTest {

        @Test
        public void shouldDownloadAndSaveAirportCVSFile(){
            AirportsCSVSheetDownloader.downloadCSV();
        }
}