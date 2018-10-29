package com.skyobserver.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {

    public static final String ACTUAL_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/airports.csv";
    public static final String NEW_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/new_airports.csv";
    public static final String CRON_SCHEDULE_EXPRESSION = "0 0 12 * * ?";
    public static final String NAME_OF_FLIGHT_LOOKUP_SERVICE_HEADER = "Ocp-Apim-Subscription-Key";
    public static final String FLIGHT_LOOKUP_API_KEY = "***REMOVED***";
    public static String FLIGHT_LOOKUP_HOST_URL = "https://flightlookup.azure-api.net/v1/xml/TimeTable/";

}
