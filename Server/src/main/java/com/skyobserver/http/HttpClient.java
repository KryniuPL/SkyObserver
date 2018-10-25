package com.skyobserver.http;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpClient {

    private static final MediaType DATA_FORMAT = MediaType.parse("application/json");
    private static OkHttpClient client;
    private static Map<String, String> httpHeaders = new HashMap<>();
    private static final String API_KEY = "TEMP";

    public HttpClient() {
        client = new OkHttpClient();
        httpHeaders.put("content-type", "application/json");
        httpHeaders.put("accept", "application/json");
        httpHeaders.put("appKey", API_KEY);
        httpHeaders.put("cache-control", "no-cache");
    }

    public Response doPut(String url, String inputData) throws IOException {
        RequestBody requestBody = RequestBody.create(DATA_FORMAT, inputData);
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .headers(Headers.of(httpHeaders))
                .build();
        return client.newCall(request).execute();
    }

    public Response doPost(String url, String inputData) throws IOException {
        RequestBody requestBody = RequestBody.create(DATA_FORMAT, inputData);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .headers(Headers.of(httpHeaders))
                .build();
        return client.newCall(request).execute();
    }

    public Response doGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .headers(Headers.of(httpHeaders))
                .build();
        return client.newCall(request).execute();
    }
}
