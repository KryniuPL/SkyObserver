package com.skyobserver.repository;

import com.skyobserver.http.HttpClient;
import com.skyobserver.model.Baggage;
import com.skyobserver.service.html.BaggageParser;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import static com.skyobserver.service.html.BaggageParser.BAGGAGE_HTML_FILE;
import static com.skyobserver.service.html.BaggageParser.ENCODING;

public class BaggageRepository {


    private static final String SKYSCANNER_BAGGAGE_INFORMATION_WEBSITE = "https://www.skyscanner.net/news/tips/check-in-luggage-size-and-weight-restrictions";
    private static final Logger logger = LoggerFactory.getLogger(BaggageRepository.class);
    private BaggageParser baggageParser = new BaggageParser();

    private static HttpClient httpClient = new HttpClient();

    public Baggage getBaggageObjectByAirlineName(String airlineName) throws IOException {
        return baggageParser.getBaggageObjectFromHTMLFile(airlineName);
    }


    public static void initializeRepository() throws IOException {
        logger.info("Downloading baggage information file");
        ResponseBody responseBody = httpClient.doGet(SKYSCANNER_BAGGAGE_INFORMATION_WEBSITE, Headers.of());
        FileUtils.writeStringToFile(BAGGAGE_HTML_FILE, responseBody.string(), ENCODING);
    }


}
