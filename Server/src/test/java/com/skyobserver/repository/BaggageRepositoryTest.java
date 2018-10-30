package com.skyobserver.repository;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.skyobserver.config.ServerConfiguration.BAGGAGE_HTML_FILE_PATH;
import static org.junit.Assert.*;

public class BaggageRepositoryTest {

    private BaggageRepository baggageRepository = new BaggageRepository();

    @Test
    public void shouldCreateHtmlFile() throws IOException {
        baggageRepository.initializeRepository();
        File htmlFile = new File(BAGGAGE_HTML_FILE_PATH);
        assertTrue(htmlFile.exists());
    }
}