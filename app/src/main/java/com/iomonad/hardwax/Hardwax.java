package com.iomonad.hardwax;

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

class Hardwax {
    private static final Map<String, String> selection;
    static {
        selection = new HashMap<String, String>() {{
            /* New releases */
            put("new_releases","http://hardwax.com/feeds/news.json");
            put("back_stock","https://hardwax.com/feeds/back-in-stock.json");
            /* Fine Selection */
            put("basic_channels","https://hardwax.com/feeds/basic-channel.json");
            put("chicago_oldschool","https://hardwax.com/feeds/chicago-oldschool.json");
            put("collector_items","https://hardwax.com/feeds/collectors-items.json");
            put("colundi_everyone","https://hardwax.com/feeds/colundi-everyone.json");
            put("detroit","https://hardwax.com/feeds/detroit.json");
            put("detroit_house","https://hardwax.com/feeds/detroit-house.json");
            put("digital","https://hardwax.com/feeds/digital.json");
            put("disco","https://hardwax.com/feeds/disco.json");
            put("drum_and_bass","https://hardwax.com/feeds/drum-n-bass.json");
            put("drexciya","https://hardwax.com/feeds/drexciya.json");
            put("early_electronics","https://hardwax.com/feeds/early-electronic.json");
            put("essentials","https://hardwax.com/feeds/essentials.json");
            put("electro","https://hardwax.com/feeds/electro.json");
            put("electronic","https://hardwax.com/feeds/electronic.json");
            put("exclusives","https://hardwax.com/feeds/exclusives.json");
            put("grime","https://hardwax.com/feeds/grime.json");
            put("honest_john","https://hardwax.com/feeds/honest-jons.json");
            put("house","https://hardwax.com/feeds/house.json");
            put("iridial_discs","https://hardwax.com/feeds/irdial-discs.json");
            put("mego","https://hardwax.com/feeds/mego.json");
            put("mew_wave","https://hardwax.com/feeds/new-wave.json");
            put("outernational","https://hardwax.com/feeds/outernational.json");
            put("perlon","https://hardwax.com/feeds/perlon.json");
            put("reissues","https://hardwax.com/feeds/reissues.json");
            put("surgeon","https://hardwax.com/feeds/surgeon.json");
            put("techno","https://hardwax.com/feeds/techno.json");
            /* Labels */
            put("d_labels","https://hardwax.com/feeds/section/d.json");
            put("euro_labels","https://hardwax.com/feeds/section/euro.json");
            put("uk_labels","https://hardwax.com/feeds/section/uk.json");
            put("us_labels","https://hardwax.com/feeds/section/us.json");
            put("reggae_dub","https://hardwax.com/feeds/section/reggae.json");
            /* Miscellaneous */
            put("merchandise","https://hardwax.com/feeds/section/merchandise.json");
        }};
    }
}
