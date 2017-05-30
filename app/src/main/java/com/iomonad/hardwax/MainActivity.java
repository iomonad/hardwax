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

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import fr.arnaudguyon.xmltojsonlib.XmlToJson;
import com.iomonad.hardwax.client.RequestHandler;
import com.iomonad.hardwax.utils.DescParser;

public class MainActivity extends AppCompatActivity {

    /* Classname for debugging */
    private String TAG = MainActivity.class.getSimpleName();

    /* Private components instances */
    private ProgressDialog pDialog;
    public ListView lv;

    ArrayList<HashMap<String,String>> feedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feedList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

         new GetFeed().execute();
    }


    /* Async thread to get hardwax feed */
    private class GetFeed extends AsyncTask<Void, Void, Void> {


        private String feed_path = "http://hardwax.com/feeds/news/";
        int feed_nums = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            RequestHandler rh = new RequestHandler();
            String stringRes = rh.execRequest(feed_path); /* Raw xml unserialized */

            if(stringRes != null) {
                try {
                    XmlToJson xjs = new XmlToJson.Builder(stringRes)
                            .forceList("rss").build();
                    JSONObject jsonObj = xjs.toJson(); /* Convert stream to json object */
                    Log.i(TAG,"Got object: " + xjs.toString());

                    JSONObject rssObject = jsonObj.getJSONObject("rss");
                    if(rssObject != null) {
                        JSONObject chanObject = rssObject.getJSONObject("channel");
                        if(chanObject != null) {
                            /*  Our feed array */
                            JSONArray feedArray = chanObject.getJSONArray("item");
                            Log.i(TAG, "Got feed array: " + feedArray);
                            /* Processing feed */
                            feed_nums = feedArray.length();
                            for(int i = 0; i < feed_nums; i++) {
                                JSONObject cursor = feedArray.getJSONObject(i);

                                /* Temporary hash map to store values */
                                HashMap<String, String> feed = new HashMap<>();

                                /* Current document modelized in soup */
                                Document p = Jsoup.parse(cursor.get("description").toString());
                                Elements dsoup = p.getElementsByTag("em");

                                /* Fill the map*/
                                feed.put("title", cursor.get("title").toString());
                                feed.put("description", dsoup.text());
                                feed.put("link", cursor.get("guid").toString());
                                /* Push it to our MainActivity list array*/
                                feedList.add(feed);
                            }
                        }
                    }

                } catch (final JSONException e) {
                    Log.e(TAG,"Json error: " + e.getMessage());
                } finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    String.format("Got %s items.", (int) feed_nums),
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("Internet Error");
                        alertDialog.setMessage("Couldn't get data from server, please check your internet connection.");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        finish(); /* Close app because you need internet */
                                    }
                                });
                        alertDialog.show();
                    }
                });

            } return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            /* Close wait after async exec */
            if (pDialog.isShowing())
                pDialog.dismiss();

            /* @Desc: Update Json result in the adapter
            * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, feedList,
                    R.layout.list_item, new String[]{"title","description","link"},
                    new int[] {R.id.title, R.id.description, R.id.link});
            lv.setAdapter(adapter); /* Pipe adapter to list */
        }
    }

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

