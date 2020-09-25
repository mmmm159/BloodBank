package com.example.bloodbank.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloodbank.R;
import com.example.bloodbank.data.model.article.ArticleData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

     private Context context;
     private List<ArticleData> articleDataList;


    public ArticlesAdapter( Context context,List<ArticleData> articleDataList){

        this.context=context;
        this.articleDataList=articleDataList;

    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ArticleViewHolder articleViewHolder =
                new ArticleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_article,
                        parent,false));
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {

        ArticleData articleData = articleDataList.get(position);

        String imageUrl = articleData.getThumbnailFullPath();
        boolean isFavorite = articleData.getIsFavourite();
        String articleTitle = articleData.getTitle();



        holder.articleBackgroundCheckbox.setChecked(isFavorite);
        Glide.with(context).load(imageUrl).into(holder.articleImageBackground);
        holder.articleTxtViewArticleTitle.setText(articleTitle);



    }

    @Override
    public int getItemCount() {
        return articleDataList.size();
    }


     class ArticleViewHolder extends RecyclerView.ViewHolder{




        @BindView(R.id.item_article_image_background)
        ImageView articleImageBackground;
        @BindView(R.id.item_article_txt_view_article_title)
        TextView articleTxtViewArticleTitle;
        @BindView(R.id.item_article_check_box_favorite)
        CheckBox articleBackgroundCheckbox;

//         ImageView articleImageBackground;
//         TextView articleTxtViewArticleTitle;
//         CheckBox articleBackgroundCheckbox;


        private ArticleViewHolder(@NonNull View itemView) {
            super(itemView);


            ButterKnife.bind(this,itemView);

//            articleImageBackground = itemView.findViewById(R.id.item_article_image_background);
//            articleTxtViewArticleTitle = itemView.findViewById(R.id.item_article_txt_view_article_title);
//            articleBackgroundCheckbox = itemView.findViewById(R.id.item_article_check_box_favorite);


        }
    }
}
