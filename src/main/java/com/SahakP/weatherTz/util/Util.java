package com.SahakP.weatherTz.util;

import com.SahakP.weatherTz.model.Log;

import java.util.List;

public class Util {
    public static long getMedianQueryTime(List<Log> logs) {
        long sum = 0;
        for (Log log : logs) {
            sum += log.getDuration();
        }

        return sum == 0L ? sum : sum/logs.size();
    }
}
