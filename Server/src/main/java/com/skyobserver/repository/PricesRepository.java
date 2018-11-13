package com.skyobserver.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.*;
import com.skyobserver.http.HttpClient;
import com.skyobserver.model.Price;
import com.skyobserver.schedulers.AirportsUpdateScheduler;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private HttpClient httpClient = new HttpClient();
    private ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(PricesRepository.class);

    public ObjectNode getFlightPrice(String currency, String originAirportIATA, String destinationAirportIATA, String departureDate, String returnDate) throws IOException {
        ResponseBody responseBody = httpClient.doGet(buildPriceRequestURL(currency, originAirportIATA, destinationAirportIATA, departureDate, returnDate),
                Headers.of(Map.of(X_MASHAPE_KEY_HEADER, SKYSCANNER_API_KEY, X_MASHAPE_HOST_HEADER, SKYSCANNER_HOST_NAME)));
        logger.info(String.valueOf(responseBody.toString()));

        String responseJson = responseBody.string();

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
                .min().orElse(0);

        Price price = new Price(minimumValue, currency);
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("value",price.getValue());
        if(minimumValue == 0){
            objectNode.put("currency", "No data");
        }
        else objectNode.put("currency", price.getCurrency());
        return objectNode;
    }

    public String formatDateWithDashes(String date) {
        String[] strings = date.split("T");
        return strings[0];
    }

    public String buildPriceRequestURL(String currency, String originAirportIATA, String destinationAirportIATA, String departureDate, String returnDate){
        departureDate = formatDateWithDashes(departureDate);
        returnDate = formatDateWithDashes(returnDate);
        return SKYSCANNER_BROWSE_QUOTES_API_URL + currency + "/en-US/" + originAirportIATA + AIRPORTS_POSTFIX + destinationAirportIATA + AIRPORTS_POSTFIX + departureDate + "/" + returnDate;
    }


}
