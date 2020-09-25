package com.example.bloodbank.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bloodbank.R;

public class SliderFragmentAdapter extends PagerAdapter {

private Context mContext;
int[] mViews = new int[]{
        R.layout.slider_layout_1,R.layout.slider_layout_2, R.layout.slider_layout_3};
private LayoutInflater mLayoutInflater;

public SliderFragmentAdapter(Context context){

    mContext=context;
    mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
}
    @Override
    public int getCount() {
        return mViews.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mLayoutInflater.inflate(mViews[position],container,false);


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }
}
