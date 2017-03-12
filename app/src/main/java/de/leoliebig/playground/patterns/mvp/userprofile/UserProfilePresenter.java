package de.leoliebig.playground.patterns.mvp.userprofile;

import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.List;

import de.leoliebig.playground.data.DataSource;
import de.leoliebig.playground.data.net.models.User;
import de.leoliebig.playground.patterns.mvp.BasePresenter;

/**
 * Implements the view and business logic for loading and displaying user profiles.
 * <p>
 * Created by Leo on 02.07.2016.
 */
public class UserProfilePresenter extends BasePresenter implements DataSource.LoadListener<User> {

    private static final String TAG = UserProfilePresenter.class.getSimpleName();

    //use a weak reference here to avoid leaking the activity in case the presenter
    // survives the GC (e.g. because of asynchronous background operations)
    private WeakReference<UserProfileMvp.View> view;

    private int userId;
    private DataSource<User> userService;

    private UserProfileMvp.Model userProfile;

    /**
     * Creates a new instances tied to the passed {@link UserProfileMvp.View} and {@link DataSource}.
     *
     * @param view        The {@link UserProfileMvp.View} to update. Must not be <code>null</code>.
     * @param userService A {@link DataSource} to read {@link User}s from. Must not be <code>null</code>.
     * @param userId      The unique id of the user to show. Must be larger than <code>0</code>.
     * @throws IllegalArgumentException in case the passed view is <code>null</code> or the user id is smaller <code>1</code>.
     */
    public UserProfilePresenter(@NonNull final UserProfileMvp.View view,
                                @NonNull final DataSource<User> userService,
                                @IntRange(from = 1) final int userId) {

        if (view == null)
            throw new IllegalArgumentException("The passed view must not be null");

        if (userId < 1)
            throw new IllegalArgumentException("Invalid user id");

        this.view = new WeakReference<>(view);
        this.userService = userService;
        this.userId = userId;

    }

    @Override
    public void present() {

        if (userProfile == null) {
            loadData();
            return;
        }

        updateView();
    }

    /**
     * Loads the data of the specified user and updates the view.
     *
     * @param userId The unique id of the user to show. Must be larger than <code>0</code>.
     * @throws IllegalArgumentException in case the user id is smaller <code>1</code>.
     */
    public void loadUser(@IntRange(from = 1) final int userId) {

        if (userId < 1)
            throw new IllegalArgumentException("Invalid user id");

        if (isViewAttached(view)) {
            view.get().showLoading();
        }

        this.userId = userId;
        userProfile = null;

        loadData();

    }

    private void updateView() {

        if (!isViewAttached(view))
            return;

        view.get().showUserInformation(
                userProfile.getFirstName(),
                userProfile.getLastName(),
                userProfile.getBiography()
        );

        loadAvatar();
    }

    private void loadData() {
        userService.load(userId, this);
    }

    private void loadAvatar() {

        if (!isViewAttached(view))
            return;

        ImageView imgAvatar = view.get().getAvatarView();

        Glide.with(imgAvatar.getContext())
                .load(userProfile.getAvatarUrl().toString())
                .centerCrop()
                .crossFade()
                .into(imgAvatar);
    }

    @Override
    public void onViewAttach(Bundle outState) {
        //noop
    }

    @Override
    public void onViewDetach(Bundle savedInstanceState) {
        //noop
    }

    @Override
    public void onLoaded(long id, User data, int resultCode) {
        //TODO add abstraction for result codes and encapsulate the data source
        if (resultCode == 200) {
            userProfile = data;
            updateView();
            loadAvatar();
        } else {
            Log.e(TAG, "loadData: " + resultCode);

            if (isViewAttached(view)) {
                view.get().showError("Error loading user " + id + ": " + resultCode);
            }
        }
    }

    @Override
    public void onLoadError(long id, Throwable t) {
        t.printStackTrace();
        Log.e(TAG, "loadData: " + t);

        if (isViewAttached(view)) {
            view.get().showError("Connection error");
        }
    }

    @Override
    public void onListLoaded(int page, List<User> data, int code) {
        //noop
    }

    @Override
    public void onListLoadError(int page, Throwable t) {
        //noop
    }
}
