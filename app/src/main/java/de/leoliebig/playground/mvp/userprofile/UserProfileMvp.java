package de.leoliebig.playground.mvp.userprofile;

import android.graphics.Bitmap;

import java.net.URL;

/**
 * Created by Leo on 02.02.2017.
 */

public abstract class UserProfileMvp {

    public interface View{

        void showUserInformation(String firstName, String lastName, String biography);

        void showUserAvatar(Bitmap avatar);

    }

    public interface Model{

        String getFirstName();

        String getLastName();

        URL getAvatarUrl();

        String getBiography();

    }

}
