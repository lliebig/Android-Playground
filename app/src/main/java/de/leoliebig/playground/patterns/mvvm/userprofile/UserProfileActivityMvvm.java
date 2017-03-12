package de.leoliebig.playground.patterns.mvvm.userprofile;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import de.leoliebig.playground.R;
import de.leoliebig.playground.data.net.clients.UserServiceClient;
import de.leoliebig.playground.databinding.BindingUserProfile;

public class UserProfileActivityMvvm extends AppCompatActivity {

    private static final String TAG = UserProfileActivityMvvm.class.getSimpleName();

    private UserProfileViewModel viewModel;
    private int userId = 2;

    private Observable.OnPropertyChangedCallback viewModelListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new UserProfileViewModel(new UserServiceClient(), userId);
        viewModelListener = createViewModelListener();

        BindingUserProfile binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile_mvvm);
        binding.setViewModel(viewModel);

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.addOnPropertyChangedCallback(viewModelListener);
    }


    @Override
    protected void onPause() {
        super.onPause();
        viewModel.removeOnPropertyChangedCallback(viewModelListener);
    }

    private Observable.OnPropertyChangedCallback createViewModelListener() {
        return new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, final int id) {
                if (id == UserProfileViewModel.PROPERTY_ERROR_MESSAGE) {
                    Log.e(TAG, viewModel.getErrorMessage() );
                } else if (id == UserProfileViewModel.PROPERTY_USER_ID) {
                    Log.d(TAG, "User id changed: " + viewModel.getUserId());
                }
            }
        };
    }


}
