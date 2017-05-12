package de.leoliebig.playground.data.realm.models;

import android.support.annotation.NonNull;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by Leo on 11.05.2017.
 */

public class Measurement extends RealmObject {

    private String name;
    private long timestampStart; //TODO make primary key
    private long timestampEnd;

    @Required
    private String ssid;

    private RealmList<MeasurementPeriod> periods;

    public Measurement(@NonNull final String name) {
        this.name = name;
    }

    public Measurement() {
        //required for realm
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestampStart() {
        return timestampStart;
    }

    public void setTimestampStart(long timestampStart) {
        this.timestampStart = timestampStart;
    }

    public long getTimestampEnd() {
        return timestampEnd;
    }

    public void setTimestampEnd(long timestampEnd) {
        this.timestampEnd = timestampEnd;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public List<MeasurementPeriod> getPeriods() {
        return periods;
    }

    public void setPeriods(RealmList<MeasurementPeriod> periods) {
        this.periods = periods;
    }
}
