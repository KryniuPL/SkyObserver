package com.skyobserver.service.html;

import com.skyobserver.model.Baggage;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

import static com.skyobserver.config.ServerConfiguration.BAGGAGE_HTML_FILE_PATH;

public class BaggageParser {

    public static final String ENCODING = "UTF-8";
    public static final File BAGGAGE_HTML_FILE = new File(BAGGAGE_HTML_FILE_PATH);
    private static final String TABLE_CSS_ELEMENT = "table";
    private static final String HTML_ROWS = "tr";
    private static final String HTML_COLUMN = "td";

    private static String readFileContentToString() throws IOException {
        return FileUtils.readFileToString(BAGGAGE_HTML_FILE, ENCODING);
    }

    public Baggage getBaggageObjectFromHTMLFile(String airlineName) throws IOException {
        Baggage baggage = Baggage.NOT_FOUND;
        String htmlFile = readFileContentToString();
        Document htmlDocument = Jsoup.parse(htmlFile);

        Element table = htmlDocument.selectFirst(TABLE_CSS_ELEMENT);
        Elements rows = table.select(HTML_ROWS);

        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select(HTML_COLUMN);
            if (cols.get(0).text().equals(airlineName)) {
                baggage = new Baggage(cols.get(1).text(), cols.get(2).text(), cols.get(3).text());
            }
        }
        return baggage;
    }


}
