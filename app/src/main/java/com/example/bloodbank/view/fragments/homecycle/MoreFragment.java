package com.example.bloodbank.view.fragments.homecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.utilities.HelperMethods;
import com.example.bloodbank.view.activities.UserActivity;
import com.example.bloodbank.view.fragments.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreFragment extends BaseFragment {

    @BindView(R.id.more_fragment_txt_view_more)
    TextView moreFragmentTxtViewMore;
    @BindView(R.id.more_fragment_txt_view_favourites)
    TextView moreFragmentTxtViewFavourites;
    @BindView(R.id.more_fragment_txt_view_contact_us)
    TextView moreFragmentTxtViewContactUs;
    @BindView(R.id.more_fragment_txt_view_about)
    TextView moreFragmentTxtViewAbout;
    @BindView(R.id.more_fragment_txt_view_rate_app)
    TextView moreFragmentTxtViewRateApp;
    @BindView(R.id.more_fragment_txt_view_notification_settings)
    TextView moreFragmentTxtViewNotificationSettings;
    @BindView(R.id.more_fragment_txt_view_logout)
    TextView moreFragmentTxtViewLogout;

    public MoreFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initFragment();
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.more_fragment_txt_view_more, R.id.more_fragment_txt_view_favourites, R.id.more_fragment_txt_view_contact_us, R.id.more_fragment_txt_view_about, R.id.more_fragment_txt_view_rate_app, R.id.more_fragment_txt_view_notification_settings, R.id.more_fragment_txt_view_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.more_fragment_txt_view_more:
                break;
            case R.id.more_fragment_txt_view_favourites:
                break;
            case R.id.more_fragment_txt_view_contact_us:
                HelperMethods.replaceFragment(baseActivity.getSupportFragmentManager(),R.id.activity_home_frame
                ,new ContactUsMoreFragment());
                break;
            case R.id.more_fragment_txt_view_about:
                break;
            case R.id.more_fragment_txt_view_rate_app:
                break;
            case R.id.more_fragment_txt_view_notification_settings:
                HelperMethods.replaceFragment(baseActivity.getSupportFragmentManager(),R.id.activity_home_frame
                        ,new NotificationSettingMoreFragment());
                break;
            case R.id.more_fragment_txt_view_logout:

                startActivity(new Intent(baseActivity, UserActivity.class));
                break;
        }
    }
}
