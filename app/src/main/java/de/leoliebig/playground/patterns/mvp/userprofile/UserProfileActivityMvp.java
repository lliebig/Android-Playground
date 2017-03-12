package de.leoliebig.playground.patterns.mvp.userprofile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import de.leoliebig.playground.R;
import de.leoliebig.playground.data.net.clients.UserServiceClient;

public class UserProfileActivityMvp extends AppCompatActivity implements UserProfileMvp.View, View.OnClickListener {

    private View containerLoading;
    private View containerContent;
    private ImageView imgAvatar;
    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvBiography;
    private Button btnShowNextUser;

    private UserProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_mvp);

        findViews();
        presenter = new UserProfilePresenter(this, new UserServiceClient(), getRandomUserId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.present();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        presenter.onViewAttach(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        presenter.onViewDetach(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showUserInformation(final String firstName, final String lastName, final String biography) {

        containerContent.setVisibility(View.VISIBLE);
        containerLoading.setVisibility(View.GONE);

        tvFirstName.setText(firstName);
        tvLastName.setText(lastName);
        tvBiography.setText(biography);

    }

    @Override
    public ImageView getAvatarView() {
        return imgAvatar;
    }

    @Override
    public void showLoading() {

        containerContent.setVisibility(View.INVISIBLE);
        containerLoading.setVisibility(View.VISIBLE);

    }

    @Override
    public void showError(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void findViews() {

        imgAvatar = (ImageView) findViewById(R.id.imgAvatar);
        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvLastName = (TextView) findViewById(R.id.tvLastName);
        tvBiography = (TextView) findViewById(R.id.tvBiography);
        btnShowNextUser = (Button) findViewById(R.id.btnShowNextUser);
        containerContent = findViewById(R.id.containerProfile);
        containerLoading = findViewById(R.id.containerLoadingIndicator);

    }

    private int getRandomUserId() {
        return (new Random().nextInt(10) + 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowNextUser:
                presenter.loadUser(getRandomUserId());
                break;
            default:
                throw new AssertionError("Unexpected click from " + v);
        }
    }
}
