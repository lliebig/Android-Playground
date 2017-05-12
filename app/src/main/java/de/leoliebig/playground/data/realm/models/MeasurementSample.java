package de.leoliebig.playground.data.realm.models;

import io.realm.RealmObject;

/**
 * Created by Leo on 11.05.2017.
 */

public class MeasurementSample extends RealmObject {

    private String name;
    private long timestamp;
    private int dbm;

    private MeasurementPeriod period;

    public MeasurementSample(MeasurementPeriod period) {
        this.period = period;
    }

    public MeasurementSample() {
        //required for realm
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getDbm() {
        return dbm;
    }

    public void setDbm(int dbm) {
        this.dbm = dbm;
    }

    public MeasurementPeriod getPeriod() {
        return period;
    }

}
