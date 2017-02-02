package de.leoliebig.playground.mvp.userprofile;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import de.leoliebig.playground.mvp.BasePresenter;

/**
 * TODO
 * Created by Leo on 02.07.2016.
 */
public class UserProfilePresenter extends BasePresenter {

    private WeakReference<UserProfileMvp.View> userView;

    /**
     * Creates a new instances tied to the passed view.
     * @param userProfileView Must not be <code>null</code>.
     * @throws IllegalArgumentException in case the passed view is <code>null</code>.
     */
    public UserProfilePresenter(@NonNull final UserProfileMvp.View userProfileView){

        if(userProfileView==null)
            throw new IllegalArgumentException("The passed view must not be null");

        this.userView = new WeakReference<>(userProfileView);

    }

    @Override
    public void onViewAttach(Bundle outState) {
        //TODO
    }

    @Override
    public void onViewDetach(Bundle savedInstanceState) {
        //TODO
    }
}
