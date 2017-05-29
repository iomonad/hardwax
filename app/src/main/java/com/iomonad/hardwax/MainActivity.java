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
import com.iomonad.hardwax.client.RequestHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;



public class MainActivity extends AppCompatActivity {

    /* Classname for debugging */
    private String TAG = MainActivity.class.getSimpleName();

    /* Private components instances */
    private ProgressDialog pDialog;
    private ListView lv;

    private String feed_path = "http://hardwax.com/feeds/news/";

    ArrayList<HashMap<String,String>> feedList;

    /* Async thread to get hardwax feed */
    private class GetFeed extends AsyncTask<Void, Void, Void> {

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
            String jsonRes = rh.execRequest(feed_path);

            if(jsonRes != null) {
                Log.e(TAG,"Got response from server: " + jsonRes);
                try {
                    // Todo: Extract json value from converted xml
                } finally {
                    Log.e(TAG, "Json parsing error !");
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feedList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new GetFeed().execute();
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

