package com.skyobserver.util;

import com.skyobserver.model.Airport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class AirportsCollectionsFilter {

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
