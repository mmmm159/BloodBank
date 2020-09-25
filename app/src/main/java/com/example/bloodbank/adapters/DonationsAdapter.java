package com.example.bloodbank.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.example.bloodbank.R;
import com.example.bloodbank.data.model.donation.DonationData;
import com.example.bloodbank.utilities.HelperMethods;
import com.example.bloodbank.view.fragments.homecycle.DonationFormDetailsFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DonationsAdapter extends RecyclerView.Adapter<DonationsAdapter.DonationViewHolder> {


    private AppCompatActivity context;
    private List<DonationData> donationDataList;

    public static int clickedItemId;


    public DonationsAdapter(AppCompatActivity context, List<DonationData> donationDataList) {

        this.context = context;
        this.donationDataList = donationDataList;

    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DonationViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_donation,parent,false)
        );

    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {

        DonationData donationData = donationDataList.get(position);

        String patientName = donationData.getPatientName();
        String bloodType = donationData.getBloodType().getName();
        String hospitalName = donationData.getHospitalName();
        String City = donationData.getCity().getName();

        holder.itemDonationTxtViewCircleBlood.setText(bloodType);
        holder.itemDonationTxtViewPatientName.setText(patientName);
        holder.itemDonationTxtViewHospital.setText(hospitalName);
        holder.itemDonationTxtViewCity.setText(City);

    }

    @Override
    public int getItemCount() {
        return donationDataList.size();
    }


    class DonationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_donation_txt_view_patient_name)
        TextView itemDonationTxtViewPatientName;
        @BindView(R.id.item_donation_txt_view_hospital)
        TextView itemDonationTxtViewHospital;
        @BindView(R.id.item_donation_txt_view_city)
        TextView itemDonationTxtViewCity;
        @BindView(R.id.item_donation_txt_view_circle_blood)
        TextView itemDonationTxtViewCircleBlood;
        @BindView(R.id.swipe_reveal)
        LinearLayout swipeLayout;




        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);


            ButterKnife.bind(this,itemView);

            swipeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    int position = getAdapterPosition();
                  clickedItemId =  donationDataList.get(position).getId();
                    HelperMethods.replaceFragment(context.getSupportFragmentManager(),
                            R.id.activity_home_frame,new DonationFormDetailsFragment());
                }
            });
        }





    }
}
