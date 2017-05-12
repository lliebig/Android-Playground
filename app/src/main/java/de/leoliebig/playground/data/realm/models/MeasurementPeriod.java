package de.leoliebig.playground.data.realm.models;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by Leo on 11.05.2017.
 */

public class MeasurementPeriod extends RealmObject {

    private long timestampStart;
    private long timestampEnd;

    @Required
    private String ssid;

    private Measurement measurement;

    private RealmList<MeasurementSample> samples;

    public MeasurementPeriod(Measurement measurement) {
        this.measurement = measurement;
    }

    public MeasurementPeriod() {
        //required for realm
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

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public List<MeasurementSample> getSamples() {
        return samples;
    }

    public void setSamples(RealmList<MeasurementSample> samples) {
        this.samples = samples;
    }
}
