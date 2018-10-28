package com.skyobserver.service.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.skyobserver.model.xml.OTA_AirDetailsRS;

import java.io.IOException;


public class FlightsDeserializer {

    private JacksonXmlModule xmlModule = new JacksonXmlModule();

    public OTA_AirDetailsRS getDeserializedXML(String XML) throws IOException {
        xmlModule.setDefaultUseWrapper(false);
        ObjectMapper objectMapper = new XmlMapper(xmlModule);
        return objectMapper.readValue(XML, OTA_AirDetailsRS.class);
    }
}
