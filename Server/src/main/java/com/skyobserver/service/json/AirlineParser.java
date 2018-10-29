package com.skyobserver.service.json;


import com.google.gson.Gson;
import com.skyobserver.model.Airline;

public class AirlineParser {

    private static final int FIRST_ELEMENT = 0;
    private Gson gson = new Gson();

    public Airline getAirlineObjectFromJSONResponse(String jsonObject) {
         Airline[] airlines = gson.fromJson(jsonObject, Airline[].class);
         return airlines[FIRST_ELEMENT];
    }

}
