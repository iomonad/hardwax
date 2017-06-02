package com.iomonad.hardwax.activity;

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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.iomonad.hardwax.R;
import com.iomonad.hardwax.adapter.ArticlesAdapter;
import com.iomonad.hardwax.model.Article;
import com.iomonad.hardwax.model.ArticleResponse;
import com.iomonad.hardwax.rest.RestClient;
import com.iomonad.hardwax.rest.RestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.articles_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RestInterface apiService =
                RestClient.getClient().create(RestInterface.class);

        Call<ArticleResponse> call = apiService.getItems();

        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                final List<Article> articles = response.body().getItems();
                Log.d(TAG, String.format("Got %s articles", articles.size()));
                recyclerView.setAdapter(new ArticlesAdapter(articles, R.layout.list_items_articles,getApplicationContext()));

                ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(articles.get(position).getUrl()));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.e(TAG,"Error: "+ t.toString());
                Toast.makeText(getApplicationContext(),
                        t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressWarnings("EmptyMethod")
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}

