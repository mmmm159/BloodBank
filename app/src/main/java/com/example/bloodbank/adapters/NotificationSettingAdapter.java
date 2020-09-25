package com.example.bloodbank.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloodbank.R;
import com.example.bloodbank.data.model.article.ArticleData;
import com.example.bloodbank.data.model.cities.CityData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationSettingAdapter extends RecyclerView.Adapter<NotificationSettingAdapter.NotificationDataViewHolder> {

    private Context context;
    private List<CityData> dataList;
    private List<String> oldIds;
    public List<Integer> newIds = new ArrayList<>();


    public NotificationSettingAdapter(Context context, List<CityData> dataList, List<String> oldIds) {

        this.context = context;
        this.dataList = dataList;
        this.oldIds = oldIds;

    }

    @NonNull
    @Override
    public NotificationDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        NotificationDataViewHolder notificationDataViewHolder =
                new NotificationDataViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notification_setting,
                        parent, false));
        return notificationDataViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationDataViewHolder holder, int position) {

        CityData data = dataList.get(position);

        String name = data.getName();
        holder.itemNotificationSetting.setText(name);



        int id = data.getId();

        for (int i = 0; i < oldIds.size(); i++) {

            if (id == Integer.parseInt(oldIds.get(i))) {
                holder.itemNotificationSetting.setChecked(true);
                newIds.add(id);
            }
        }

//        if (holder.itemNotificationSetting.isChecked()){
//
//            for (int i = 0; i < newIds.size(); i++) {
//                if (id!=newIds.get(i)) {
//                    newIds.add(id);
//                }
//            }
//
//        }
//        else {
//            for (int i = 0; i <newIds.size() ; i++) {
//
//                if (id==newIds.get(i)) newIds.remove(i);
//            }
        //     }

        holder.itemNotificationSetting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (!isChecked){
                    for (int i = 0; i < newIds.size(); i++) {

                        if (newIds.get(i)==id) {
                            newIds.remove(i);
                            break;

                        }

                    }
                }
                else {
                    newIds.add(id);


                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class NotificationDataViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.item_notification_setting)
        CheckBox itemNotificationSetting;




        private NotificationDataViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);


//            itemNotificationSetting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//
//                    if (!isChecked){
//                        for (int i = 0; i < newIds.size(); i++) {
//
//                            if (newIds.get(i)==dataList.get(getAdapterPosition()).getId()) {
//                                newIds.remove(i);
//                                break;
//
//                            }
//
//                        }
//                    }
//                    else {
//                        newIds.add(dataList.get(getAdapterPosition()).getId());
//
//
//                    }
//                }
//            });
        }



//        @OnClick(R.id.item_notification_setting)
//        public void onClick() {
//
//            if (!itemNotificationSetting.isChecked()) {
//
//                for (int i = 0; i < newIds.size(); i++) {
//
//                    if (newIds.get(i).equals(dataList.get(getAdapterPosition()).getId())) {
//                        newIds.remove(i);
//                        break;
//                    }
//
//                }
//            } else {
//                newIds.add(dataList.get(getAdapterPosition()).getId());
//            }


//        }


    }
}


