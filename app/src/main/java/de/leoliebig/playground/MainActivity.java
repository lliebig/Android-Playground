package de.leoliebig.playground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import de.leoliebig.playground.patterns.mvp.userprofile.UserProfileActivityMvp;

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
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new AssertionError("Unexpeted click from view: " + view);
        }
    }
}
