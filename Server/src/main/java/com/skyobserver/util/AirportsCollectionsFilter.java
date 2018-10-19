package com.skyobserver.util;

import com.skyobserver.model.Airport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class AirportsCollectionsFilter {

    private static final String ACTUAL_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/airports.csv";
    private static final String NEW_AIRPORTS_CSV_FILE_PATH = "src/main/resources/csv/new_airports.csv";

    public static List<Airport> filterListWithMediumAndLargeType(HashSet<Airport> listToFilter) {
        return listToFilter.stream()
                .filter(a -> a.getType().equals("medium_airport") || a.getType().equals("large_airport"))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static HashSet<Airport> getNewAirports(HashSet<Airport> oldAirports, HashSet<Airport> newAirports) throws IOException {
        newAirports.removeAll(oldAirports);
        return newAirports;
    }

    public static HashSet<Airport> getOldAirports(HashSet<Airport> oldAirports, HashSet<Airport> newAirports) throws IOException {
        oldAirports.removeAll(newAirports);
        return oldAirports;
    }
}
