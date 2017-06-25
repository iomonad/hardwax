package com.iomonad.hardwax.adapter;

/*
 * Copyright (c) 2017 iomonad <iomonad@riseup.net>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import com.iomonad.hardwax.R;
import com.iomonad.hardwax.model.Article;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    private final List<Article> articles;
    private final int rowLayout;

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        final LinearLayout articlesLayout;
        final TextView articleTitle;
        //TextView articleUrl;
        final TextView articleDescription;
        final TextView articlePrice;

        public ArticleViewHolder(View v) {
            super(v);
            articlesLayout = (LinearLayout) v.findViewById(R.id.articles_layout);
            articleTitle = (TextView) v.findViewById(R.id.title);
            //articleUrl = (TextView) v.findViewById(R.id.url);
            articleDescription = (TextView) v.findViewById(R.id.description);
            articlePrice = (TextView) v.findViewById(R.id.price);
        }
    }

    public ArticlesAdapter(List<Article> articles,
                           Context context) {
        this.articles = articles;
        this.rowLayout = R.layout.list_items_articles;
        Context context1 = context;
    }

    @Override
    public ArticlesAdapter.ArticleViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ArticleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ArticleViewHolder holder, final int position) {
        holder.articleTitle.setText(articles.get(position).getTitle()); /* Retrieve from rest client */
        //holder.articleUrl.setText(articles.get(position).getUrl());
        holder.articleDescription.setText(articles.get(position).getDescription());
        holder.articlePrice.setText(articles.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
