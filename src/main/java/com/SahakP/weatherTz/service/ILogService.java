package com.SahakP.weatherTz.service;

import com.SahakP.weatherTz.model.Log;

import java.util.Date;
import java.util.List;

public interface ILogService {
    void setLog(Log log);
    List<Log> getLogsByQuery(String query);
    List<Log> getLogsByDate(Date date);
    List<Log> getLogsByDateGap(Date dateFrom, Date dateTo);
}
