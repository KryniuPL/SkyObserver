package com.skyobserver.repository;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class FlightsRepositoryTest {

    private FlightsRepository flightsRepository = new FlightsRepository();

    @Test
    public void shouldReturnValidRequestUrl(){
        String originAirportIATA = "WAW";
        String destinationAirportIATA = "LHR";
        String departureDate = "20181120";
        String typeOfConnection = "DIRECT";
        assertEquals(FlightsRepository.buildRequestUrl(originAirportIATA,destinationAirportIATA,departureDate,typeOfConnection), "https://flightlookup.azure-api.net/v1/xml/TimeTable/WAW/LHR/20181120/?7Day=N&Connection=DIRECT&Compression=ALL&Sort=Departure&Time=ANY&Interline=N&NoFilter=N&ExpandResults=Y");
    }

    @Test
    public void shouldReturnValidLocalDateTimeFromString(){
        String date = "2018-11-15T07:35:00";
        LocalDateTime formattedDate = flightsRepository.formatDateFromStringToLocalDateTime(date);
        assertEquals(formattedDate.getDayOfMonth(), 15);
        assertEquals(formattedDate.getMonth(), Month.NOVEMBER);
        assertEquals(formattedDate.getYear(), 2018);
        assertEquals(formattedDate.getHour(), 7);
        assertEquals(formattedDate.getMinute(), 35);
        assertEquals(formattedDate.getSecond(), 0);
    }

    @Test
    public void shouldReturnValidDurationTime(){
        String duration = "PT3H00M";
        Duration calculatedDuration = flightsRepository.getDurationObjectFromStringExpression(duration);
        System.out.println(calculatedDuration);
        assertEquals(calculatedDuration.toHours(), 3);
        assertEquals(calculatedDuration.toMinutes(), 0);
    }
}