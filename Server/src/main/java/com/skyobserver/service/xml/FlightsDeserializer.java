package com.skyobserver.service.xml;

import com.skyobserver.model.Flight;
import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.StringReader;

public class FlightsDeserializer {

    private DocumentBuilder documentBuilder;
    private InputSource inputSource;

    public FlightsDeserializer() throws ParserConfigurationException {
        this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource inputSource = new InputSource();
    }

    public void getSingleObjectInstance(String xmlObject) throws Exception {
        inputSource.setCharacterStream(new StringReader(xmlObject));
        Document document = documentBuilder.parse(String.valueOf(inputSource));

    }
}
