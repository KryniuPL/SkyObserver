package com.skyobserver.repository;

import com.skyobserver.http.HttpClient;
import com.skyobserver.model.Baggage;
import okhttp3.Headers;
import okhttp3.ResponseBody;

import java.io.IOException;

public class BaggageRepository {

    private static String ALL_BAGGAGE_DATA;
    private final String SKYSCANNER_BAGGAGE_INFORMATION_WEBSITE = "https://www.skyscanner.net/news/tips/check-in-luggage-size-and-weight-restrictions";
    private HttpClient httpClient = new HttpClient();
    public Baggage getBaggageObjectByAirlineIATACode(){
        return null;
    }

    private void initializeRepository() throws IOException {
        ResponseBody responseBody = httpClient.doGet(SKYSCANNER_BAGGAGE_INFORMATION_WEBSITE, Headers.of());
        ALL_BAGGAGE_DATA = responseBody.string();
    }
}
