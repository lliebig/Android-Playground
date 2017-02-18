package de.leoliebig.playground.patterns.mvp.userprofile;

import android.graphics.Bitmap;

import java.net.URL;

/**
 * Created by Leo on 02.02.2017.
 */

public abstract class UserProfileMvp {

    public interface View {

        void showUserInformation(String firstName, String lastName, String biography);

        void showUserAvatar(Bitmap avatar);

        void showLoading();

        void showError(String message);

    }

    public interface Model {

        String getFirstName();

        String getLastName();

        URL getAvatarUrl();

        String getBiography();

    }

}
