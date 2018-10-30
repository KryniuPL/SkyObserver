package com.skyobserver.repository;

import com.skyobserver.http.HttpClient;
import com.skyobserver.model.Baggage;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static com.skyobserver.config.ServerConfiguration.BAGGAGE_HTML_FILE_PATH;

public class BaggageRepository {

    private static final String ENCODING = "UTF-8";
    private static final String SKYSCANNER_BAGGAGE_INFORMATION_WEBSITE = "https://www.skyscanner.net/news/tips/check-in-luggage-size-and-weight-restrictions";
    private static final Logger logger = LoggerFactory.getLogger(BaggageRepository.class);
    private static final File baggageHtmlFile = new File(BAGGAGE_HTML_FILE_PATH);
    private HttpClient httpClient = new HttpClient();

    public Baggage getBaggageObjectByAirlineName(String airlineName) throws IOException {
        Baggage baggage = Baggage.NOT_FOUND;
        String htmlFile = readFileContentToString();
        Document htmlDocument = Jsoup.parse(htmlFile);
        Element table = htmlDocument.selectFirst("tftable");
        Elements rows = table.select("tr");

        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            if(cols.get(0).text().equals(airlineName)){
                baggage = new Baggage(cols.get(1).text(), cols.get(2).text(), cols.get(3).text());
            }
        }
        return baggage;
    }


    public void initializeRepository() throws IOException {
        logger.info("Downloading baggage information file");
        ResponseBody responseBody = httpClient.doGet(SKYSCANNER_BAGGAGE_INFORMATION_WEBSITE, Headers.of());
        FileUtils.writeStringToFile(baggageHtmlFile, responseBody.string(), ENCODING);
    }

    private String readFileContentToString() throws IOException {
        return FileUtils.readFileToString(baggageHtmlFile, ENCODING);
    }
}
