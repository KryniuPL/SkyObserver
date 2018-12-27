package com.skyobserver.service.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PricesParser {

    private static final String NAME_OF_QUOTES_JSON_ARRAY = "Quotes";
    private static final String FIELD_NAME_WITH_QUOTE_INFORMATION = "MinPrice";

    public JsonArray convertApiResponseToJsonArray(String apiResponse){
        JsonElement element = new JsonParser().parse(apiResponse);
        JsonObject jsonObject = element.getAsJsonObject();
        return jsonObject.getAsJsonArray(NAME_OF_QUOTES_JSON_ARRAY);
    }

    public List<Double> getPricesFromApiResponse(JsonArray jsonArray) {
        List<Double> pricesList = new ArrayList<>();
        try {
            for (JsonElement singleElement : jsonArray) {
                JsonObject quoteObject = singleElement.getAsJsonObject();
                pricesList.add(quoteObject.get(FIELD_NAME_WITH_QUOTE_INFORMATION).getAsDouble());
            }
        } catch (NullPointerException e) {
            pricesList = Collections.emptyList();
        }
        return pricesList;
    }
}
