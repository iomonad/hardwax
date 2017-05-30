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
import android.support.annotation.NonNull;

public class HtmlParser {

    private static final String TAG = "XmlToJson";
    private Document root_document;

    public class Builder {

        /* Document is stored */
        private Document document;

        public Builder(@NonNull String s) {
            root_document = Jsoup.parse(s);
        }

        /* Small helper to parse document */
        public void parse(String s) {
            this.document = Jsoup.parse(s);
            return;
        }

        public String getDescription() {
            return this.document.select("em").first().toString();
        }

        public HtmlParser build() {
            return new HtmlParser(this);
        }
    }

    public HtmlParser(Builder b) {
        b.build();
    }
}
