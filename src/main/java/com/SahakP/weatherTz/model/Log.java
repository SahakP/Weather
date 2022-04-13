package com.SahakP.weatherTz.model;

import com.SahakP.weatherTz.Diction;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Diction.TABLE_LOG, schema = Diction.SCHEMA)
public class Log {
    private Long id;
    private String query;
    private Date date;
    private Long duration;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
