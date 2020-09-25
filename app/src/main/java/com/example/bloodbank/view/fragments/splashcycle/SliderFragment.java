package com.example.bloodbank.view.fragments.splashcycle;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;
import com.example.bloodbank.adapters.SliderFragmentAdapter;
import com.example.bloodbank.view.activities.UserActivity;
import com.example.bloodbank.view.fragments.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SliderFragment extends BaseFragment {


    public SliderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =  inflater.inflate(R.layout.fragment_slider, container, false);

        final ViewPager viewPager = view.findViewById(R.id.slider_view_pager);
        SliderFragmentAdapter sliderFragmentAdapter = new SliderFragmentAdapter(getContext());
        viewPager.setAdapter(sliderFragmentAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position==viewPager.getAdapter().getCount()-1){
                    startActivity(new Intent(getContext(), UserActivity.class));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

}
