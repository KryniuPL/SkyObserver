package com.skyobserver.repository;

import com.google.gson.JsonArray;
import com.skyobserver.config.CacheConfiguration;
import com.skyobserver.http.HttpClient;
import com.skyobserver.model.CachedPrice;
import com.skyobserver.service.json.PricesParser;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Repository
public class PricesRepository {

    private static final String SKYSCANNER_BROWSE_QUOTES_API_URL = "https://skyscanner-skyscanner-flight-search-v1.p.mashape.com/apiservices/browsequotes/v1.0/US/";
    private static final String X_MASHAPE_KEY_HEADER = "X-Mashape-Key";
    private static final String X_MASHAPE_HOST_HEADER = "X-Mashape-Host";
    private static final String SKYSCANNER_API_KEY = "***REMOVED***";
    private static final String SKYSCANNER_HOST_NAME = "skyscanner-skyscanner-flight-search-v1.p.mashape.com";
    private static final String AIRPORTS_POSTFIX = "-sky/";
    private static final String CACHE_KEY_SEPARATOR = "-";
    private HttpClient httpClient = new HttpClient();
    private PricesParser pricesParser = new PricesParser();
    private final Cache<String, CachedPrice> priceCache;
    private static final Logger logger = LoggerFactory.getLogger(PricesRepository.class);

    public PricesRepository() {
        CacheManager cacheManager = CacheConfiguration.pricesCacheManager();
        this.priceCache = cacheManager.getCache("cachedFlightPrices", String.class, CachedPrice.class);
    }

    public CachedPrice getFlightPrice(String currency, String originAirportIATA, String destinationAirportIATA, String departureDate, String returnDate) throws IOException {
        CachedPrice cachedPrice = priceCache.get(originAirportIATA + CACHE_KEY_SEPARATOR + destinationAirportIATA);
        if (cachedPrice == null) {
            cachedPrice = getFlightPriceFromApi(currency, originAirportIATA, destinationAirportIATA, departureDate, returnDate);
            if (cachedPrice.getValue() > 0){
                priceCache.put(cachedPrice.getOriginPointOfRoute() + CACHE_KEY_SEPARATOR + cachedPrice.getDestinationPointOfRoute(), cachedPrice);
            }
        }
        return cachedPrice;
    }

    private CachedPrice getFlightPriceFromApi(String currency, String originAirportIATA, String destinationAirportIATA, String departureDate, String returnDate) throws IOException {
        ResponseBody responseBody = httpClient.doGet(buildPriceRequestURL(currency, originAirportIATA, destinationAirportIATA, departureDate, returnDate),
                Headers.of(Map.of(X_MASHAPE_KEY_HEADER, SKYSCANNER_API_KEY, X_MASHAPE_HOST_HEADER, SKYSCANNER_HOST_NAME)));

        JsonArray jsonArray = pricesParser.convertApiResponseToJsonArray(responseBody.string());
        List<Double> pricesList = pricesParser.getPricesFromApiResponse(jsonArray);

        double minimumValue = pricesList
                .stream()
                .mapToDouble(v -> v)
                .min().orElse(0);

        return new CachedPrice(originAirportIATA, destinationAirportIATA, minimumValue, currency);
    }

    public String formatDateWithDashes(String date) {
        String[] strings = date.split("T");
        return strings[0];
    }

    public String buildPriceRequestURL(String currency, String originAirportIATA, String destinationAirportIATA, String departureDate, String returnDate) {
        departureDate = formatDateWithDashes(departureDate);
        returnDate = formatDateWithDashes(returnDate);
        return SKYSCANNER_BROWSE_QUOTES_API_URL + currency + "/en-US/" + originAirportIATA + AIRPORTS_POSTFIX + destinationAirportIATA + AIRPORTS_POSTFIX + departureDate + "/" + returnDate;
    }


}
