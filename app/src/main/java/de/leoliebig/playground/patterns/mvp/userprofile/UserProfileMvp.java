package de.leoliebig.playground.patterns.mvp.userprofile;

import android.widget.ImageView;

import java.net.URL;

/**
 * Defines the MVP contract. The interfaces for the View and Model are used by the Presenter
 * and ensure loose coupling and interchangeability of views and models.
 * <p>
 * Created by Leo on 02.02.2017.
 */

public abstract class UserProfileMvp {

    public interface View {

        void showUserInformation(String firstName, String lastName, String biography);

        void showLoading();

        void showError(String message);

        ImageView getAvatarView();

    }

    public interface Model {

        String getFirstName();

        String getLastName();

        URL getAvatarUrl();

        String getBiography();

    }

}
