package com.SahakP.weatherTz.service;

import com.SahakP.weatherTz.model.Log;
import com.SahakP.weatherTz.repository.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogService implements ILogService {

    @Autowired
    private LogRepo logRepo;

    @Override
    public void setLog(Log log) {logRepo.save(log); }

    @Override
    public List<Log> getLogsByQuery(String query) {
        return logRepo.findAllByQuery(query);
    }

    @Override
    public List<Log> getLogsByDate(Date date) {
        return logRepo.findByDate(date);
    }

    @Override
    public List<Log> getLogsByDateGap(Date dateFrom, Date dateTo) {
        return logRepo.findAllByDateBetween(dateFrom, dateTo);
    }
}
