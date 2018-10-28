package com.skyobserver.http;

import okhttp3.*;

import java.io.IOException;

public class HttpClient {

    private static OkHttpClient client;

    public HttpClient() {
        client = new OkHttpClient();
    }

    public Response doGet(String url, Headers headers) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .headers(headers)
                .build();
        return client.newCall(request).execute();
    }
}
