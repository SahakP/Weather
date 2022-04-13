package com.SahakP.weatherTz.controller;

import com.SahakP.weatherTz.model.Log;
import com.SahakP.weatherTz.service.ILogService;
import com.SahakP.weatherTz.service.IWeatherService;
import com.SahakP.weatherTz.service.ServiceOpenWeather;
import com.SahakP.weatherTz.service.ServiceWeatherApi;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@RestController
public class WeatherController {
    @Autowired
    private ILogService logService;

    private static ArrayList<IWeatherService> services;
    static {
        services = new ArrayList<>();
        services.add(new ServiceOpenWeather());
        services.add(new ServiceWeatherApi());
    }

    @PostMapping("/getWeather")
    String getWeather(@RequestBody String city) {
        JsonArray resultArray = new JsonArray();
        Log log = new Log();
        log.setQuery(city);
        log.setDate(new Date());

        long qStart = Calendar.getInstance().getTimeInMillis();
        for (IWeatherService service : services) {
            String resStr = service.getWeather(city);
            JsonObject convertedObject = new Gson().fromJson(resStr, JsonObject.class);
            resultArray.add(convertedObject);
        }
        long qEnd = Calendar.getInstance().getTimeInMillis();
        log.setDuration(qEnd - qStart);
        logService.setLog(log);


        return resultArray.toString();
    }


    @GetMapping("/getServices")
    String getServices() {
        JsonArray resultArray = new JsonArray();

        for (IWeatherService service : services) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", service.getServiceName());
            jsonObject.addProperty("url", service.getServiceUrl());
            jsonObject.addProperty("isActive", !TextUtils.isEmpty(service.getWeather("Moscow")));
            resultArray.add(jsonObject);
        }

        return resultArray.toString();
    }

}

