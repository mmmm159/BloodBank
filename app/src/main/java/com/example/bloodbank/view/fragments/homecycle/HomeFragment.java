package com.example.bloodbank.view.fragments.homecycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.bloodbank.R;
import com.example.bloodbank.adapters.FragmentViewPagerAdapter;
import com.example.bloodbank.view.fragments.BaseFragment;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends BaseFragment {


    TabLayout tabLayout;
    ViewPager viewPager;

    public HomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initFragment();
        //ButterKnife.bind(getActivity());
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        String articles = getString(R.string.fragment_home_articles);
        String donationOrders = getString(R.string.fragment_home_donation_orders);

        tabLayout = view.findViewById(R.id.fragment_home_tab_layout);
        viewPager = view.findViewById(R.id.fragment_home_viewpager);

        FragmentViewPagerAdapter fragmentViewPagerAdapter =
                new FragmentViewPagerAdapter(getChildFragmentManager());

        fragmentViewPagerAdapter.addFragments(new ArticlesHomeFragment(), articles);
        fragmentViewPagerAdapter.addFragments(new DonationRequestHomeFragment(), donationOrders);

        viewPager.setAdapter(fragmentViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        return view;

    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
