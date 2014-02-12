package com.ninetwozero.bf4intel.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.SessionStore;
import com.ninetwozero.bf4intel.base.ui.BaseFragment;
import com.ninetwozero.bf4intel.datatypes.TrackingNewProfileEvent;
import com.ninetwozero.bf4intel.factories.UrlFactory;
import com.ninetwozero.bf4intel.ui.login.LoginActivity;
import com.ninetwozero.bf4intel.utils.BusProvider;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    public static HomeFragment newInstance(final Bundle data) {
        final HomeFragment fragment = new HomeFragment();
        fragment.setArguments(data);
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        BusProvider.getInstance().register(this);
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        final View baseView = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(baseView);
        return baseView;
    }

    @Override
    public void onClick(final View v) {
        final Activity activity = getActivity();
        activity.startActivityForResult(
            new Intent(activity, LoginActivity.class),
            LoginActivity.REQUEST_PROFILE
        );
    }

    @Subscribe
    public void onStartedTrackingNewProfile(final TrackingNewProfileEvent event) {
        final View view = getView();
        if (view == null) {
            return;
        }
        initialize(view);
    }

    private void initialize(final View view) {
        if (SessionStore.hasUserId()) {
            setupTracker(view);
        } else {
            setupGuestMode(view);
        }
    }

    private void setupTracker(final View view) {
        view.findViewById(R.id.wrap_guest).setVisibility(View.GONE);

        final View wrap = view.findViewById(R.id.wrap_tracker);
        final TextView usernameTextView = (TextView) wrap.findViewById(R.id.selected_user_username);
        final ImageView gravatarImageView = (ImageView) wrap.findViewById(R.id.selected_user_gravatar);

        usernameTextView.setText(SessionStore.getUsername());
        Picasso.with(getActivity()).load(UrlFactory.buildGravatarUrl(SessionStore.getGravatarHash())).into(gravatarImageView);

        wrap.findViewById(R.id.button_select_another_account).setOnClickListener(this);
        wrap.setVisibility(View.VISIBLE);
    }

    private void setupGuestMode(final View view) {
        view.findViewById(R.id.wrap_tracker).setVisibility(View.GONE);

        final View wrap = view.findViewById(R.id.wrap_guest);
        wrap.findViewById(R.id.button_select_account).setOnClickListener(this);
        wrap.setVisibility(View.VISIBLE);
    }
}