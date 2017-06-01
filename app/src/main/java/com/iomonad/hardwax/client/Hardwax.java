package com.iomonad.hardwax.client;

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

import java.util.Map;
import java.util.HashMap;

public class Hardwax {
    public static final Map<String, String> selection;
    static {
        selection = new HashMap<String, String>() {{
            /* New releases */
            put("new_releases","http://hardwax.com/feeds/news/");
            put("back_stock","https://hardwax.com/feeds/back-in-stock/");
            /* Fine Selection */
            put("basic_channels","https://hardwax.com/feeds/basic-channel/");
            put("chicago_oldschool","https://hardwax.com/feeds/chicago-oldschool/");
            put("collector_items","https://hardwax.com/feeds/collectors-items/");
            put("colundi_everyone","https://hardwax.com/feeds/colundi-everyone/");
            put("detroit","https://hardwax.com/feeds/detroit/");
            put("detroit_house","https://hardwax.com/feeds/detroit-house/");
            put("digital","https://hardwax.com/feeds/digital/");
            put("disco","https://hardwax.com/feeds/disco/");
            put("drum_and_bass","https://hardwax.com/feeds/drum-n-bass/");
            put("drexciya","https://hardwax.com/feeds/drexciya/");
            put("early_electronics","https://hardwax.com/feeds/early-electronic/");
            put("essentials","https://hardwax.com/feeds/essentials/");
            put("electro","https://hardwax.com/feeds/electro/");
            put("electronic","https://hardwax.com/feeds/electronic/");
            put("exclusives","https://hardwax.com/feeds/exclusives/");
            put("grime","https://hardwax.com/feeds/grime/");
            put("honest_john","https://hardwax.com/feeds/honest-jons/");
            put("house","https://hardwax.com/feeds/house/");
            put("iridial_discs","https://hardwax.com/feeds/irdial-discs/");
            put("mego","https://hardwax.com/feeds/mego/");
            put("mew_wave","https://hardwax.com/feeds/new-wave/");
            put("outernational","https://hardwax.com/feeds/outernational/");
            put("perlon","https://hardwax.com/feeds/perlon/");
            put("reissues","https://hardwax.com/feeds/reissues/");
            put("surgeon","https://hardwax.com/feeds/surgeon/");
            put("techno","https://hardwax.com/feeds/techno/");
            /* Labels */
            put("d_labels","https://hardwax.com/feeds/section/d/");
            put("euro_labels","https://hardwax.com/feeds/section/euro/");
            put("uk_labels","https://hardwax.com/feeds/section/uk/");
            put("us_labels","https://hardwax.com/feeds/section/us/");
            put("reggae_dub","https://hardwax.com/feeds/section/reggae/");
            /* Miscellaneous */
            put("merchandise","https://hardwax.com/feeds/section/merchandise/");
        }};
    }
}
