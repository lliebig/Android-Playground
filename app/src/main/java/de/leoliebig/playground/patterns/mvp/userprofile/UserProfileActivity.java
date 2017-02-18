package de.leoliebig.playground.patterns.mvp.userprofile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import de.leoliebig.playground.R;

public class UserProfileActivity extends AppCompatActivity implements UserProfileMvp.View {

    private ImageView imgAvatar;
    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvBiography;

    private UserProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        findViews();

        presenter = new UserProfilePresenter(this); //TODO pass data source to get the user data from
    }

    @Override
    protected void onResume() {
        super.onResume();
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
    public void showUserInformation(String firstName, String lastName, String biography) {

        tvFirstName.setText(firstName);
        tvLastName.setText(lastName);
        tvBiography.setText(biography);

    }

    @Override
    public void showUserAvatar(Bitmap avatar) {

        if (avatar != null)
            imgAvatar.setImageBitmap(avatar);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String message) {

    }

    private void findViews() {

        imgAvatar = (ImageView) findViewById(R.id.imgAvatar);
        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvLastName = (TextView) findViewById(R.id.tvLastName);
        tvBiography = (TextView) findViewById(R.id.tvBiography);

    }
}
