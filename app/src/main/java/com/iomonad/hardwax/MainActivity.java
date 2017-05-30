package com.iomonad.hardwax;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import fr.arnaudguyon.xmltojsonlib.XmlToJson;

import com.iomonad.hardwax.client.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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
                                /* Extract values */
                                feed.put("title", cursor.get("title").toString());
                                feed.put("description", cursor.get("description").toString());
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
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server.",
                                Toast.LENGTH_LONG)
                                .show();
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
            // Todo: Parse object through list
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish(); /* Flush to avoid battery drain */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}

