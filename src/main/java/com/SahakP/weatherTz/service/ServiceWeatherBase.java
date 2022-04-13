package com.SahakP.weatherTz.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;

public class ServiceWeatherBase {
    protected String makeGetRequest(HttpGet httpGet, URI uri) {
        String result = "";

        CloseableHttpClient client = HttpClients.createDefault();

        try {
            httpGet.setURI(uri);
            CloseableHttpResponse response = client.execute(httpGet);
            result = EntityUtils.toString(response.getEntity());
            client.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }

        return result;
    }
}
