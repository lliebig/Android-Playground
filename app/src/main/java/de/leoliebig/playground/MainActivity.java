package de.leoliebig.playground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import de.leoliebig.playground.patterns.mvp.userprofile.UserProfileActivityMvp;
import de.leoliebig.playground.patterns.mvvm.userprofile.UserProfileActivityMvvm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnUserProfileMvp:
                startActivity(new Intent(this, UserProfileActivityMvp.class));
                break;
            case R.id.btnUserProfileMvvm:
                startActivity(new Intent(this, UserProfileActivityMvvm.class));
                break;
            case R.id.btnRealm:
                startActivity(new Intent(this, RealmActivity.class));
                break;
            default:
                throw new AssertionError("Unexpected click from view: " + view);
        }
    }
}
