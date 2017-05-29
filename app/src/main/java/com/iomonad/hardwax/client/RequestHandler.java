package com.iomonad.hardwax.client;

/**
 * Created by iomonad on 5/29/17.
 */


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.util.Log;


public class RequestHandler {

    /* Classname variable for traceback */
    private static final String TAG = RequestHandler.class.getSimpleName();


    public RequestHandler() {
        /* Void Constructor */
    }

    /* @Desc: Exec http request and return raw buffer response to string
    * */
    public String execRequest(String targetUrl) {
        String response = null;
        try {
            URL url = new URL(targetUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            /* Convert Server responses to string*/
            response = streamToString(new BufferedInputStream(conn.getInputStream()));
        } catch (MalformedURLException e) {
            Log.e(TAG,"MalformedURLException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        } return response;
    }

    /* @Desc: Convert raw buffer response to string
    * */
    private String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } return sb.toString();
    }
}
