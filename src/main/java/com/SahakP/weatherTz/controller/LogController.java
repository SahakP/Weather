package com.SahakP.weatherTz.controller;

import com.SahakP.weatherTz.model.Log;
import com.SahakP.weatherTz.service.ILogService;
import com.SahakP.weatherTz.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
public class LogController {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    @Autowired
    private ILogService logService;

    @PostMapping("/getLogByCity")
    String getLogByCity(@RequestBody String city) {
        List<Log> logs = logService.getLogsByQuery(city);
        JsonObject jo = new JsonObject();
        jo.addProperty("query", city);
        jo.addProperty("count", logs.size());
        jo.addProperty("medianDuration", Util.getMedianQueryTime(logs));
        return jo.toString();
    }

    @PostMapping("/getLogByDate")
    String getLogByDate(@RequestBody String date) {

        try {
            Date dateParsed = formatter.parse(date);
            List<Log> logs = logService.getLogsByDate(dateParsed);
            JsonObject jo = new JsonObject();
            jo.addProperty("query", date);
            jo.addProperty("count", logs.size());
            jo.addProperty("medianDuration", Util.getMedianQueryTime(logs));
            return jo.toString();
        } catch (Exception e) {
            return "Not Valid Date, use dd/mm/yyyy";
        }
    }

    @PostMapping("/getLogFromTo")
    String getLogFromTo(@RequestBody String dataStr) {

        try {
            JsonObject data =  new Gson().fromJson(dataStr, JsonObject.class);
            Date dateFromParsed = formatter.parse(data.get("from").getAsString());
            Date dateToParsed = formatter.parse(data.get("to").getAsString());
            List<Log> logs = logService.getLogsByDateGap(dateFromParsed, dateToParsed);
            JsonObject jo = new JsonObject();
            jo.addProperty("query", data.toString());
            jo.addProperty("count", logs.size());
            jo.addProperty("medianDuration", Util.getMedianQueryTime(logs));
            return jo.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
