package com.SahakP.weatherTz.service;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class ServiceWeatherApi extends ServiceWeatherBase implements IWeatherService {
    public static final String NAME = "Weather Api";
    public static final String URL = "http://api.weatherapi.com/v1/current.json";
    private static final String APIKEY = "267d46bd186e498384c142639221204";

    @Override
    public String getWeather(String city) {
        String result = "";
        HttpGet httpGet = new HttpGet(URL);

        try {
            URI uri = new URIBuilder(httpGet.getURI()).addParameter("q", city)
                    .addParameter("key", APIKEY)
                    .build();

            result = super.makeGetRequest(httpGet, uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String getServiceName() {
        return NAME;
    }

    @Override
    public String getServiceUrl() {
        return URL;
    }
}
