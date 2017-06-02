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

public class Article {
    @SerializedName("url")
    private String url;
    @SerializedName("title")
    private String title;
    @SerializedName("id")
    private  String id;
    @SerializedName("date_published")
    private String date_published;
    @SerializedName("content_html")
    private String content_html;

    /* Basic constructor */
    public Article(String url, String title, String id,
                   String date_published, String content_html) {
        this.url = url;
        this.title = title;
        this.id = id;
        this.date_published = date_published;
        this.content_html = content_html;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getDate_published() {
        return date_published;
    }

    public String getContent_html() {
        return content_html;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public void setContent_html(String content_html) {
        this.content_html = content_html;
    }
}
