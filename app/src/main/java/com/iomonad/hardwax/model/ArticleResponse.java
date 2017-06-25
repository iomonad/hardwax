package com.iomonad.hardwax.model;

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

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleResponse {
    @SerializedName("version")
    private final String version;
    @SerializedName("title")
    private final String title;
    @SerializedName("home_page_url")
    private final String home_page_url;
    @SerializedName("feed_url")
    private final String feed_url;
    @SerializedName("items")
    private final List<Article> items;

    public ArticleResponse(String version, String title, String home_page_url, String feed_url, List<Article> items) {
        this.version = version;
        this.title = title;
        this.home_page_url = home_page_url;
        this.feed_url = feed_url;
        this.items = items;
    }

    public String getVersion() {
        return version;
    }

    public String getTitle() {
        return title;
    }

    public String getHome_page_url() {
        return home_page_url;
    }

    public String getFeed_url() {
        return feed_url;
    }

    public List<Article> getItems() {
        return items;
    }

    public int getNumberItems() {
        return items.size();
    }
}
