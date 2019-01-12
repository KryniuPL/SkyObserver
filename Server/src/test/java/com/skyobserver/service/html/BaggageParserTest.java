package com.skyobserver.service.html;

import com.skyobserver.model.Baggage;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class BaggageParserTest {

    private BaggageParser baggageParser = new BaggageParser();

    @Test
    public void shouldReturnNullBaggageObject() throws IOException {
        Baggage baggage = baggageParser.getBaggageObjectFromHTMLFile("Lot Airlines");
        assertEquals(baggage, Baggage.NOT_FOUND);
    }

    @Test
    public void shouldReturnNotNullBaggageObject() throws IOException {
        Baggage baggage = baggageParser.getBaggageObjectFromHTMLFile("Aer Lingus");
        assertNotEquals(baggage, Baggage.NOT_FOUND);
        System.out.println(baggage.toString());
    }
}