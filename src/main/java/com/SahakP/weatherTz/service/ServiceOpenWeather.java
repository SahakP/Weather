package com.SahakP.weatherTz.service;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class ServiceOpenWeather extends ServiceWeatherBase implements IWeatherService{
    public static final String NAME = "Open weather";
    public static final String URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String APIKEY = "cba1061682cbcff71fb2e7d51112ea19";

    @Override
    public String getWeather(String city) {
        String result = "";
        HttpGet httpGet = new HttpGet(URL);
        try {
            URI uri = new URIBuilder(httpGet.getURI())
                    .addParameter("q", city)
                    .addParameter("appid", APIKEY)
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
