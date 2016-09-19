package com.example.user.either.Data;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zouheir on 28/08/2016.
 */
public class ServersAPI extends AsyncTask<String, Integer, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        // mybar. setProgress(values[0]);
    }

    @Override
    protected String doInBackground(String... params) {

        String resullt = "ok";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String jsonStr = "";
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            jsonStr = buffer.toString();
            return jsonStr;
        } catch (Exception e) {
            Log.e("Error HTTP Connection", e.toString());
        }
        return resullt;
    }

    // RESULT
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.v("ApiResult ", result);

    }

}
