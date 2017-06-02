package com.iomonad.hardwax.scrape;

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

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

/* @Description: Trivial class to parse
*   serialized html easily.
* */

public class DescParser {
    public String getDescription(Document d) {
        Elements links = d.getElementsByTag("em");
        return links.text();
    }
    public String getImage(Document d) {
        Element img = d.select("img").last();
        return img.attr("src");
    }
}
