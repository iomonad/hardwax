package com.iomonad.hardwax.database;

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


import android.os.AsyncTask;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;

public class RestDatabase extends AsyncTask<HashMap<String,String>, Void, Boolean> {

    private DatabaseHelper dbH;

    @Override
    protected Boolean doInBackground(HashMap<String, String>... s) {
        dbH.InsertItems(s[0]);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean res) {
        if(res) {} else {}
    }
}
