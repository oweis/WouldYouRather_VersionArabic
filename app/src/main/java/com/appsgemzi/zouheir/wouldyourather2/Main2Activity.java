package com.appsgemzi.zouheir.wouldyourather2;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    Button blue_button,red_button,skip_button;
    Button like_button,comment_button,share_button;
    Controller controler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init_design();

    }

    private void init_design(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        blue_button = (Button) findViewById(R.id.blue_question);
        red_button = (Button) findViewById(R.id.red_question);
        skip_button = (Button) findViewById(R.id.skip);

        like_button = (Button) findViewById(R.id.like);
        comment_button = (Button) findViewById(R.id.comment);
        share_button = (Button) findViewById(R.id.share);

        //click events
        blue_button.setOnClickListener(this);
        red_button.setOnClickListener(this);
        skip_button.setOnClickListener(this);
        like_button.setOnClickListener(this);
        share_button.setOnClickListener(this);
        comment_button.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.blue_question:
                break;
            case R.id.red_question:
                break;
            case R.id.skip:
                break;
            case R.id.like:
                break;
            case R.id.comment:
                break;
            case R.id.share:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void answer(){

        String url = "";

        new Server().execute(url);

    }

    private void get_question(){

        String url = "";

        new get_Question().execute(url);

    }

    private void like_dislike_question(int a){
        String url = "";
        if(a == 0 ){

        }else{

        }

        new Server().execute(url);

    }

    private void getEvents(String city) {

        //clear data first
        events.clear();

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        String finalUrl = urlLeft+city+urlRight;

        JsonObjectRequest eventsRequest = new JsonObjectRequest(DownloadManager.Request.Method.GET,
                finalUrl, (JSONObject)null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                hidePDialog();

                try {
                    JSONObject eventsObject = response.getJSONObject("events");

                    JSONArray eventsArray = eventsObject.getJSONArray("event");

                    for (int i = 0; i < eventsArray.length(); i++) {
                        JSONObject jsonObject = eventsArray.getJSONObject(i);

                        //Get artist object
                        JSONObject artistObject = jsonObject.getJSONObject("artists");
                        String headlinterText = artistObject.getString("headliner");

                        //Venue Object
                        JSONObject venueObject = jsonObject.getJSONObject("venue");
                        String venueName = venueObject.getString("name");


                        //Location object
                        JSONObject locationObject = venueObject.getJSONObject("location");
                        String city = locationObject.getString("city");
                        String country = locationObject.getString("country");
                        String street = locationObject.getString("street");
                        String postalCode = locationObject.getString("postalcode");


                        //Get url image
                        JSONArray imageArray = jsonObject.getJSONArray("image");


                        //Get image
                        JSONObject largeImage = imageArray.getJSONObject(3);

                        //Get actual image url
                        String image = largeImage.getString("#text");


                        //Get started date
                        String startDate = jsonObject.getString("startDate");


                        //Get website url
                        String website = jsonObject.getString("website");


                        Event event = new Event();

                        event.setHeadLiner(headlinterText);
                        event.setVenueName(venueName);
                        event.setCity(city);
                        event.setCountry(country);
                        event.setStreet(street);
                        event.setUrl(image);
                        event.setStartDate(startDate);
                        event.setWebsite(website);


                        events.add(event);




                        Log.v("Headliner: " , headlinterText);

                    }

                    adapter.notifyDataSetChanged();








                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                hidePDialog();

            }
        });

        AppController.getInstance().addToRequestQueue(eventsRequest);


    }

























    // servers

    private class Server extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String result = "";
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
                result = buffer.toString();

            } catch (Exception e) {
                Log.e("Error HTTP Connection", e.toString());
            }
            return result;
        }

        // RESULT
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), "Message sent..", Toast.LENGTH_SHORT).show();
        }
    }

    private class get_Question extends AsyncTask<String, Integer, String> {
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


            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String result = "";
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
                result = buffer.toString();

            } catch (Exception e) {
                Log.e("Error HTTP Connection", e.toString());
            }
            return result;
        }

        // RESULT
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), "Message sent..", Toast.LENGTH_SHORT).show();
        }
    }
}
