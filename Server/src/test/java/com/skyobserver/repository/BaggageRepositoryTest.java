package com.skyobserver.repository;

import com.skyobserver.model.Baggage;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.skyobserver.config.ServerConfiguration.BAGGAGE_HTML_FILE_PATH;
import static com.skyobserver.model.Baggage.INFORMATION_ABOUT_BAGGAGE_NOT_FOUND;
import static org.junit.Assert.*;

public class BaggageRepositoryTest {

    private static final String FAKE_AIRLINE_NAME = "Kielce Airlines";
    private static final String AER_LINGUS_AIRLINE_NAME = "Aer Lingus";
    private BaggageRepository baggageRepository = new BaggageRepository();

    public BaggageRepositoryTest() throws IOException {
    }

    @Test
    public void shouldCreateHtmlFile() throws IOException {
        baggageRepository.initializeRepository();
        File htmlFile = new File(BAGGAGE_HTML_FILE_PATH);
        assertTrue(htmlFile.exists());
    }

    @Test
    public void shouldReturnInformationAboutBaggage() throws IOException {
        Baggage baggage = baggageRepository.getBaggageObjectByAirlineName(AER_LINGUS_AIRLINE_NAME);
        assertNotEquals(baggage.getFreeBaggageAllowance(), INFORMATION_ABOUT_BAGGAGE_NOT_FOUND);
        assertFalse(baggage.getExtraBaggageAllowance().isEmpty());
        assertFalse(baggage.getTechnicalParameters().isEmpty());
    }

    @Test
    public void shouldReturnEmptyBaggageObject() throws IOException {
        Baggage baggage = baggageRepository.getBaggageObjectByAirlineName(FAKE_AIRLINE_NAME);
        assertEquals(baggage.getFreeBaggageAllowance(), INFORMATION_ABOUT_BAGGAGE_NOT_FOUND);
        assertTrue(baggage.getExtraBaggageAllowance().isEmpty());
        assertTrue(baggage.getTechnicalParameters().isEmpty());
    }
}