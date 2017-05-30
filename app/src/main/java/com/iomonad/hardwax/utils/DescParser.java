package com.iomonad.hardwax.utils;

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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

public class DescParser extends AsyncTask<Document, Void, String> {

    @Override
    protected void onPreExecute(){};

    @Override
    protected String doInBackground(Document... d) {
        return "Some threaded description";
    }

    private String getDesccription(Document d) {
        Elements links = d.getElementsByTag("dom");
        return d.toString();
    }
}
