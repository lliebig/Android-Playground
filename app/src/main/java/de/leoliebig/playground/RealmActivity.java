package de.leoliebig.playground;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.Calendar;
import java.util.Random;

import de.leoliebig.playground.data.realm.models.Measurement;
import de.leoliebig.playground.data.realm.models.MeasurementPeriod;
import de.leoliebig.playground.data.realm.models.MeasurementSample;
import io.realm.Realm;
import io.realm.RealmList;

public class RealmActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

    }

    @Override
    protected void onResume() {
        super.onResume();

        createMockMeasurement();
    }

    private void createMockMeasurement() {

        realm.beginTransaction();
        Measurement measurement = new Measurement("Mocked Measurement");
//        measurement.setName("Mocked Measurement");
        measurement.setSsid("MyWifi");
        realm.commitTransaction();

        Calendar calendar = Calendar.getInstance();
        long timestampStart;
        long timestampEnd;

        calendar.set(2017, 1, 1, 12, 0, 0);
        timestampStart = calendar.getTimeInMillis();
        calendar.set(2017, 1, 1, 18, 0, 0);
        timestampEnd = calendar.getTimeInMillis();

        MeasurementPeriod period1 = new MeasurementPeriod(measurement);
//        period1.setMeasurement(measurement);
        period1.setSsid("MyWifi");
        period1.setTimestampStart(timestampStart);
        period1.setTimestampEnd(timestampEnd);
        createMockSamplesForPeriod(period1);

        calendar.set(2017, 1, 1, 18, 0, 1);
        timestampStart = calendar.getTimeInMillis();
        calendar.set(2017, 1, 2, 12, 0, 0);
        timestampEnd = calendar.getTimeInMillis();

        MeasurementPeriod period2 = new MeasurementPeriod(measurement);
//        period2.setMeasurement(measurement);
        period2.setSsid("MyNeighborsWifi");
        period2.setTimestampStart(timestampStart);
        period2.setTimestampEnd(timestampEnd);
        createMockSamplesForPeriod(period2);

        RealmList<MeasurementPeriod> periods = new RealmList<>();
        periods.add(period1);
        periods.add(period2);

        measurement.setPeriods(periods);
        measurement.setTimestampStart(period1.getTimestampStart());
        measurement.setTimestampEnd(period2.getTimestampEnd());

        realm.beginTransaction();
        realm.copyToRealm(measurement);
        realm.commitTransaction();

    }

    private void createMockSamplesForPeriod(@NonNull final MeasurementPeriod period) {

        long timestampStartSecs = period.getTimestampStart() / 1000;
        long timestampEndSecs = period.getTimestampEnd() / 1000;

        Random random = new Random();
        RealmList<MeasurementSample> samples = new RealmList<>();
        MeasurementSample sample;

        for (long i = timestampStartSecs; i < timestampEndSecs; i++) {
            sample = new MeasurementSample(period);
            sample.setTimestamp(i);
            sample.setDbm(-100 + random.nextInt(70));
            samples.add(sample);
        }

        period.setSamples(samples);

    }


}
