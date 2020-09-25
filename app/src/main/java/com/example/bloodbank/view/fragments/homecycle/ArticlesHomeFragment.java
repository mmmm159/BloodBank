package com.example.bloodbank.view.fragments.homecycle;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapters.ArticlesAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.model.article.Article;
import com.example.bloodbank.data.model.article.ArticleData;
import com.example.bloodbank.data.model.cities.City;
import com.example.bloodbank.data.model.cities.CityData;
import com.example.bloodbank.utilities.HelperMethods;
import com.example.bloodbank.utilities.OnEndLess;
import com.example.bloodbank.utilities.SharedPreference;
import com.example.bloodbank.view.fragments.BaseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesHomeFragment extends BaseFragment {


    @BindView(R.id.fragment_article_home_spinner_choose)
    Spinner fragmentArticleHomeSpinnerChoose;
    @BindView(R.id.fragment_article_home_edt_txt_search)
    EditText fragmentArticleHomeEdtTxtSearch;
    @BindView(R.id.fragment_article_home_btn_search)
    ImageButton fragmentArticleHomeBtnSearch;
    @BindView(R.id.fragment_article_home_float_btn)
    FloatingActionButton fragmentArticleHomeFloatBtn;
    @BindView(R.id.fragment_article_home_recycler)
    RecyclerView fragmentArticleHomeRecycler;

    private ApiService apiService;

    private List<ArticleData> articleDataList;

    private ArticlesAdapter articlesAdapter;
    private Integer lastPage = 0;


    public ArticlesHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initFragment();
        View view = inflater.inflate(R.layout.fragment_articles_home, container, false);
        ButterKnife.bind(this, view);
        apiService = RetrofitClient.getClient().create(ApiService.class);

        apiService.getCategories().enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                if (response.body().getStatus() == 1) {
                    List<CityData> categoriesDataList = response.body().getData();
                    List<String> categoriesTitleList = new ArrayList<>();
                    categoriesTitleList.add(baseActivity.getString(R.string.fragment_home_articles_spinner_articles_default));

                    for (int i = 0; i < categoriesDataList.size(); i++) {

                        CityData categoryData = categoriesDataList.get(i);
                        categoriesTitleList.add(categoryData.getName());
                    }

                    ArrayAdapter<String> articlesAdapter = new ArrayAdapter<>(baseActivity, R.layout.spinner_articles_color_layout
                            , categoriesTitleList);

                    articlesAdapter.setDropDownViewResource(R.layout.spinner__articles_dropdown_layout);

                    fragmentArticleHomeSpinnerChoose.setAdapter(articlesAdapter);


                }

            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });



        setUpRecyclerView();


        return view;
    }

    private void setUpRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(baseActivity);

        articleDataList = new ArrayList<>();

        OnEndLess onEndLess = new OnEndLess(layoutManager,1) {
            @Override
            public void onLoadMore(int current_page) {

              //  if (lastPage!=0)
                getPosts(current_page);
            }
        };


        articlesAdapter = new ArticlesAdapter(baseActivity, articleDataList);

        fragmentArticleHomeRecycler.setAdapter(articlesAdapter);
        fragmentArticleHomeRecycler.setLayoutManager(layoutManager);
        fragmentArticleHomeRecycler.addOnScrollListener(onEndLess);

        getPosts(1);

    }

    private void getPosts(int pageNumber) {

        String apiToken = SharedPreference.loadString(baseActivity, SharedPreference.API_TOKEN);

        apiService.getArticles(apiToken,pageNumber).enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {

                if (response.body().getStatus()==1) {

                    lastPage = response.body().getData().getLastPage();
                   articleDataList.addAll(response.body().getData().getData());
                   articlesAdapter.notifyDataSetChanged();
                }



            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.fragment_article_home_btn_search, R.id.fragment_article_home_float_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_article_home_btn_search:

                break;
            case R.id.fragment_article_home_float_btn:
                HelperMethods.replaceFragment(baseActivity.getSupportFragmentManager(), R.id.activity_home_frame, new DonationFormFragment());
                break;
        }
    }
}
