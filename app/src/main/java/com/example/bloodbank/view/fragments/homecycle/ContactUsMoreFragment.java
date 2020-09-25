package com.example.bloodbank.view.fragments.homecycle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.model.contactus.ContactUs;
import com.example.bloodbank.data.model.settings.Settings;
import com.example.bloodbank.utilities.AppSettingsData;
import com.example.bloodbank.utilities.SharedPreference;
import com.example.bloodbank.view.fragments.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsMoreFragment extends BaseFragment {

    @BindView(R.id.more_fragment_txt_view_phone_number)
    TextView moreFragmentTxtViewPhoneNumber;
    @BindView(R.id.more_fragment_txt_view_email_address)
    TextView moreFragmentTxtViewEmailAddress;
    @BindView(R.id.more_fragment_img_facebook)
    ImageView moreFragmentImgFacebook;
    @BindView(R.id.more_fragment_img_instagram)
    ImageView moreFragmentImgInstagram;
    @BindView(R.id.more_fragment_img_twitter)
    ImageView moreFragmentImgTwitter;
    @BindView(R.id.more_fragment_img_youtube)
    ImageView moreFragmentImgYoutube;
    @BindView(R.id.more_fragment_edt_txt_message_head)
    EditText moreFragmentEdtTxtMessageHead;
    @BindView(R.id.more_fragment_edt_txt_message)
    EditText moreFragmentEdtTxtMessage;
    @BindView(R.id.more_fragment_btn_send)
    Button moreFragmentBtnSend;

    private ApiService apiService;

    private String phone;
    private String email;
    private String fbLink;

    private String twLink;
    private String ytLink;
    private String instaLink;

    public ContactUsMoreFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initFragment();
        View view = inflater.inflate(R.layout.fragment_more_contact_us, container, false);
        apiService= RetrofitClient.getClient().create(ApiService.class);
        ButterKnife.bind(this, view);




        apiService.getAppSettings(SharedPreference.loadString(baseActivity,SharedPreference.API_TOKEN))
                .enqueue(new Callback<Settings>() {
                    @Override
                    public void onResponse(Call<Settings> call, Response<Settings> response) {
                        if (response.body().getStatus()==1) {
                             phone = response.body().getData().getPhone();
                             email = response.body().getData().getEmail();

                             fbLink = response.body().getData().getFacebookUrl();
                            twLink = response.body().getData().getTwitterUrl();
                            ytLink = response.body().getData().getYoutubeUrl();
                            instaLink = response.body().getData().getInstagramUrl();

                            moreFragmentTxtViewEmailAddress.setText(email);
                            moreFragmentTxtViewPhoneNumber.setText(phone);
                        }
                    }

                    @Override
                    public void onFailure(Call<Settings> call, Throwable t) {

                    }
                });


        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.more_fragment_img_facebook, R.id.more_fragment_img_instagram, R.id.more_fragment_img_twitter, R.id.more_fragment_img_youtube, R.id.more_fragment_btn_send})
    public void onViewClicked(View view) {

        Intent intent = null;
        switch (view.getId()) {

            case R.id.more_fragment_img_facebook:

                 intent = new Intent(Intent.ACTION_VIEW, Uri.parse(fbLink));
                startActivity(intent);

                break;
            case R.id.more_fragment_img_instagram:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instaLink));
                startActivity(intent);
                break;
            case R.id.more_fragment_img_twitter:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twLink));
                startActivity(intent);
                break;
            case R.id.more_fragment_img_youtube:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ytLink));
                startActivity(intent);
                break;
            case R.id.more_fragment_btn_send:

                String msgTitle = moreFragmentEdtTxtMessageHead.getText().toString();
                String msgBody = moreFragmentEdtTxtMessage.getText().toString();
                String apiToken = SharedPreference.loadString(baseActivity,SharedPreference.API_TOKEN);

                apiService.contactUs(apiToken,msgTitle,msgBody).enqueue(new Callback<ContactUs>() {
                    @Override
                    public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                        if (response.body().getStatus()==1) {
                            Toast.makeText(baseActivity,response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        if (response.body().getStatus()==0) {
                            Toast.makeText(baseActivity,response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ContactUs> call, Throwable t) {

                    }
                });
                break;
        }
    }
}
