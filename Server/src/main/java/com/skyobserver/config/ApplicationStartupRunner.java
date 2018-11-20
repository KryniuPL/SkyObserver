package com.skyobserver.config;

import com.skyobserver.repository.BaggageRepository;
import com.skyobserver.util.AirportsCSVSheetDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Starting SkyObserver application");
        logger.info("Application is starting with option names: {}", args.getOptionNames());
        //will be uncommented in the end of development and QA process
        //BaggageRepository.initializeRepository();
        //AirportsCSVSheetDownloader.downloadCSV();
    }
}
