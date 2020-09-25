package com.example.bloodbank.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.bloodbank.data.model.cities.CityData;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {


    private Context context;
    private List<CityData> dataList;

    public SpinnerAdapter(Context context, List<CityData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public String getItem(int i) {
        return dataList.get(i).getName();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
