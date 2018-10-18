package com.skyobserver.schedulers;

import com.skyobserver.util.AirportsCSVSheetDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AirportsUpdateScheduler {

    private static final Logger log = LoggerFactory.getLogger(AirportsUpdateScheduler.class);

    @Scheduled(cron = "*/100 * * * * *")
    public void scheduleUpdating(){
        AirportsCSVSheetDownloader.downloadCSV();
        log.info("Scheduler test");
    }





}
