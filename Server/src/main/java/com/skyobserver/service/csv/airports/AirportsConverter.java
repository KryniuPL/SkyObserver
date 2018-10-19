package com.skyobserver.service.csv.airports;

import com.skyobserver.model.Airport;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;

public class AirportsConverter {

    public static List<Airport> getListOfAirportsInTheRangeOf(int firstIndex, int lastIndex, List<CSVRecord> records){
        List<Airport> airportsInRange = new ArrayList<>();
        for(int i = firstIndex; i < lastIndex; i++){
            airportsInRange.add(getSingleObject(records.get(i)));
        }
        return airportsInRange;
    }

    public List<Airport> getListOfObjects(List<CSVRecord> records) {
        List<Airport> convertedAirports = new ArrayList<>();
        for (CSVRecord record : records){
            convertedAirports.add(getSingleObject(record));
        }
        return convertedAirports;
    }

    public static Airport getSingleObject(CSVRecord record) {
        long id = Long.parseLong(record.get("id"));
        String ident = record.get("ident");
        String type = record.get("type");
        String name = record.get("name");
        double latitudeDeg = Double.parseDouble(record.get("latitudeDeg"));
        double longitudeDeg = Double.parseDouble(record.get("longitudeDeg"));
        long elevationFt = Long.parseLong(record.get("elevationFt"));
        String continent = record.get("continent");
        String isoCountry = record.get("isoCountry");
        String isoRegion = record.get("isoRegion");
        String municipality = record.get("municipality");
        String scheduledService = record.get("scheduledService");
        String gpsCode = record.get("gpsCode");
        String iataCode = record.get("iataCode");
        String localCode = record.get("localCode");
        String homeLink = record.get("homeLink");
        String wikipediaLink = record.get("wikipediaLink");
        String keywords = record.get("keywords");
        return new Airport(id, ident, type, name, latitudeDeg, longitudeDeg, elevationFt, continent, isoCountry, isoRegion, municipality,scheduledService, gpsCode, iataCode, localCode, homeLink, wikipediaLink, keywords);
    }

}
