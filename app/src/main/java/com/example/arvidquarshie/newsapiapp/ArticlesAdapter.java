package com.example.arvidquarshie.newsapiapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by arvid.quarshie on 5/7/2017.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    private List<Article> articleList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, description, urlToImage;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            urlToImage = (TextView) view.findViewById(R.id.urlToImage);

        }

        @Override
        public void onClick(View v) {
            Log.d("ArticlesAdapter:", "onClick " + getPosition() + " " + articleList.size());
        }
    }


    public ArticlesAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public ArticlesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.MyViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.urlToImage.setText(article.getUrlToImage());


    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }
}