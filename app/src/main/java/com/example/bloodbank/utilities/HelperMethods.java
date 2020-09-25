package com.example.bloodbank.utilities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


import com.example.bloodbank.R;
import com.example.bloodbank.view.fragments.homecycle.ProfileFragment;

import java.util.Calendar;



public class HelperMethods {

    private static DatePickerDialog.OnDateSetListener onDateSetListener;

    public static void replaceFragment(FragmentManager fragmentManager, int container, Fragment fragment){

        fragmentManager.beginTransaction()
                .replace(container,fragment)
                .commit();

    }

    public static void setDatePicker(View view,Activity activity){

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.DAY_OF_MONTH);
                int year = calendar.get(Calendar.YEAR);

                onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        month++;
                        String m = String.valueOf(month);
                        String d = String.valueOf(day);

                        if (month<=9){
                            m = "0" + month;
                        }
                        if (day<=9){
                            d = "0" + day;
                        }

                        ((TextView) view).setText(" " + year + "-" + m + "-" + d);

                    }
                };

                DatePickerDialog dialog = new DatePickerDialog(activity
                        , android.R.style.Theme_Holo_Dialog_MinWidth
                        , onDateSetListener
                        , year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
    }
}
