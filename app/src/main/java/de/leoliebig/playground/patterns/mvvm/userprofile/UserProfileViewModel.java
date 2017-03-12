package de.leoliebig.playground.patterns.mvvm.userprofile;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

import de.leoliebig.playground.BR;
import de.leoliebig.playground.data.DataSource;
import de.leoliebig.playground.data.net.models.User;
import de.leoliebig.playground.utils.StringUtils;

/**
 * Implements the view and business logic for loading and displaying user profiles. Add a
 * {@link Observable.OnPropertyChangedCallback} to this instance to get notified about data changes.
 *
 * Created by Leo on 12.03.2017.
 */
public class UserProfileViewModel extends BaseObservable implements DataSource.LoadListener<User> {

    public static final int PROPERTY_ERROR_MESSAGE = BR.errorMessage;
    public static final int PROPERTY_USER_ID = BR.userId;

    public static final int NOT_INITIALIZED = -1;

    private static final String TAG = UserProfileViewModel.class.getSimpleName();

    private DataSource<User> userService;
    private User userProfile;

    @Bindable
    private int userId = NOT_INITIALIZED; //TODO observable

    @Bindable
    private String errorMessage = StringUtils.EMPTY;


    /**
     * Creates a new instance.
     *
     * @param userService A {@link DataSource} to read {@link User}s from. Must not be <code>null</code>.
     * @param userId      The unique id of the userProfile to show. Must be larger than <code>0</code>.
     * @throws IllegalArgumentException in case the passed view is <code>null</code> or the userProfile id is smaller <code>1</code>.
     */
    public UserProfileViewModel(@NonNull final DataSource<User> userService,
                                @IntRange(from = 1) final int userId) {

        if (userId < 1)
            throw new IllegalArgumentException("Invalid userProfile id");

        this.userService = userService;
        this.userId = userId;

        loadUserData();
    }

    @Bindable
    public int getLoadingContainerVisibility() {
        if (userProfile == null && StringUtils.isEmpty(errorMessage))
            return View.VISIBLE;
        else
            return View.GONE;
    }

    @Bindable
    public int getProfileContainerVisibility() {
        if (userProfile==null || StringUtils.notEmpty(errorMessage))
            return View.GONE;
        else
            return View.VISIBLE;
    }

    @Bindable
    public int getErrorContainerVisibility() {
        if (StringUtils.isEmpty(errorMessage))
            return View.GONE;
        else
            return View.VISIBLE;
    }

    public String getAvatarUrl() {
        if (userProfile == null)
            return "";
        else
            return userProfile.getAvatarUrl().toString();
    }

    public String getFirstName() {
        if (userProfile == null)
            return "";
        else
            return userProfile.getFirstName();
    }

    public String getLastName() {
        if (userProfile == null)
            return "";
        else
            return userProfile.getLastName();
    }

    public int getUserId() {
        return userId;
    }

    public String getBiography(){
        if (userProfile == null)
            return "";
        else
            return userProfile.getBiography();
    }

    private void loadUserData() {
        userProfile = null;
        notifyPropertyChanged(BR.loadingContainerVisibility);
        notifyPropertyChanged(BR.profileContainerVisibility);

        userService.load(userId, this);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadAvatar(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .fitCenter()
                .crossFade()
                .into(view);
    }

    public void onClickShowRandomUser(View view){
        userId = getRandomUserId();
        loadUserData();
        notifyPropertyChanged(PROPERTY_USER_ID);
    }

    public void onClickRetry(View view){
        errorMessage = StringUtils.EMPTY;
        notifyPropertyChanged(BR.errorMessage);
        updateContainerVisibilities();

        loadUserData();
    }

    @Override
    public void onLoaded(long id, User data, int resultCode) {
        //TODO add abstraction for result codes and encapsulate the data source
        if (resultCode == 200) {
            errorMessage = StringUtils.EMPTY;
            userProfile = data;
            //implicitly triggers the avatar loading
            notifyPropertyChanged(BR._all);
        } else {
            errorMessage = "Error loading userProfile " + id + ": " + resultCode;
            notifyPropertyChanged(BR.errorMessage);
            updateContainerVisibilities();
        }
    }

    @Override
    public void onLoadError(long id, Throwable throwable) {
        throwable.printStackTrace();

        errorMessage = "Loading error: " + throwable.getLocalizedMessage();
        notifyPropertyChanged(BR.errorMessage);
        updateContainerVisibilities();
    }

    private void updateContainerVisibilities() {
        notifyPropertyChanged(BR.errorContainerVisibility);
        notifyPropertyChanged(BR.profileContainerVisibility);
        notifyPropertyChanged(BR.loadingContainerVisibility);
    }

    @Override
    public void onListLoaded(int page, List<User> data, int code) {
        //noop
    }

    @Override
    public void onListLoadError(int page, Throwable t) {
        //noop
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private int getRandomUserId() {
        return (new Random().nextInt(10) + 1);
    }

}
