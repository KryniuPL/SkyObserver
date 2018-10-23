package com.skyobserver.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {

    public static final String ACTUAL_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/airports.csv";
    public static final String NEW_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/new_airports.csv";
    public static final String CRON_SCHEDULE_EXPRESSION = "0 0 12 * * ?";

}
