package com.skyobserver.util;

import com.skyobserver.model.Airport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AirportsCollectionsFilter {

    public static List<Airport> filterListWithMediumAndLargeType(List<Airport> listToFilter){
        return listToFilter.stream()
                .filter(a -> a.getType().equals("medium_airport") || a.getType().equals("large_airport"))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
