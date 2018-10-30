package com.skyobserver.repository;

import com.google.gson.*;
import com.skyobserver.http.HttpClient;
import com.skyobserver.model.Price;
import okhttp3.Headers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PricesRepository {

    private static final String SKYSCANNER_BROWSE_QUOTES_API_URL = "https://skyscanner-skyscanner-flight-search-v1.p.mashape.com/apiservices/browsequotes/v1.0/US/";
    private static final String X_MASHAPE_KEY_HEADER = "X-Mashape-Key";
    private static final String X_MASHAPE_HOST_HEADER = "X-Mashape-Host";
    private static final String SKYSCANNER_API_KEY = "***REMOVED***";
    private static final String SKYSCANNER_HOST_NAME = "skyscanner-skyscanner-flight-search-v1.p.mashape.com";
    private static final String NAME_OF_QUOTES_JSON_ARRAY = "Quotes";
    private static final String FIELD_NAME_WITH_QUOTE_INFORMATION = "MinPrice";
    private static final String AIRPORTS_POSTFIX = "-sky/";
    private static final String DATE_SEPARATOR = "-";
    private HttpClient httpClient = new HttpClient();


    public Price getFlightPrice(String currency, String originAirportIATA, String destinationAirportIATA, String departureDate, String returnDate) throws IOException {
        String responseJson = httpClient.doGet(buildPriceRequestURL(currency, originAirportIATA, destinationAirportIATA, departureDate, returnDate),
                Headers.of(Map.of(X_MASHAPE_KEY_HEADER, SKYSCANNER_API_KEY, X_MASHAPE_HOST_HEADER, SKYSCANNER_HOST_NAME))).string();

        JsonElement element = new JsonParser().parse(responseJson);
        JsonObject jsonObject = element.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray(NAME_OF_QUOTES_JSON_ARRAY);

        List<Double> pricesList = new ArrayList<>();
        for(JsonElement singleElement : jsonArray){
            JsonObject quoteObject = singleElement.getAsJsonObject();
            pricesList.add(quoteObject.get(FIELD_NAME_WITH_QUOTE_INFORMATION).getAsDouble());
        }

        double minimumValue = pricesList
                .stream()
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);

        return new Price(minimumValue, currency);
    }


    public String buildPriceRequestURL(String currency, String originAirportIATA, String destinationAirportIATA, String departureDate, String returnDate){
        departureDate = formatDateWithDashSeparators(departureDate);
        returnDate = formatDateWithDashSeparators(returnDate);
        return SKYSCANNER_BROWSE_QUOTES_API_URL + currency + "/en-US/" + originAirportIATA + AIRPORTS_POSTFIX + destinationAirportIATA + AIRPORTS_POSTFIX + departureDate + "/" + returnDate;
    }

    public String formatDateWithDashSeparators(String date){
        return date.substring(0, 4) + DATE_SEPARATOR + date.substring(4, 6) + DATE_SEPARATOR + date.substring(6, 8);
    }
}
