package com.skyobserver.service.csv;

import java.util.List;

public interface CSVDataReader {
    List<Object> getListOfObjects();
    Object getSingleObject();
}
