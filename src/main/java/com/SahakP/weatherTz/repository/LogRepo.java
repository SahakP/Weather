package com.SahakP.weatherTz.repository;

import com.SahakP.weatherTz.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogRepo extends JpaRepository<Log, Long> {
    List<Log> findAllByQuery(String query);
    List<Log> findByDate(Date date);

    @Query("select a from Log a where a.date between :dateFrom and :dateTo")
    List<Log> findAllByDateBetween(Date dateFrom, Date dateTo);
}
