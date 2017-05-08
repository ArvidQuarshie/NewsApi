package com.example.arvidquarshie.newsapiapp;

/**
 * Created by arvid.quarshie on 5/8/2017.
 */

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import adapters.TechnologyRelatedAdapter;
import model.TechnologyRelated;

public class TechnologyRelatedActivity extends AppCompatActivity {
    private String techRelatedUrl = " https://newsapi.org/v1/articles?source=techcrunch&category=technology&apiKey=7b59453e5e9848d9aeeb923a1dd581d0";

    public static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private TechnologyRelatedAdapter mAdapter;
    private TextView getResponse;
    private ImageView image;
    private TextView mTxtDisplay;
    private List<TechnologyRelated> technologyRelatedArrayList = new ArrayList<>();
    List<? extends HashMap<String, String>> technologyRelatedList;
    HashMap<String, String> categoryHashMap = new HashMap<>();
    public String TITLE, DESCRIPTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.technology_related);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getResponse = (TextView) findViewById(R.id.description3);
        mTxtDisplay = (TextView) findViewById(R.id.title3);
//        image = (ImageView)findViewById(R.id.urlToImage);

        if (isConnected()) {
            new HttpAsyncTechTask().execute(techRelatedUrl);
        } else {
            Toast.makeText(getBaseContext(), "Error" + "Please check Network Connection", Toast.LENGTH_LONG).show();
        }
        populateRecyclerView(TITLE, DESCRIPTION);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new TechnologyRelatedAdapter(technologyRelatedArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }


    public static String GET(String url) {
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            URL newsApiEndPoint = new URL(url);

            // make GET request to the given URL
            HttpsURLConnection myConnection =
                    (HttpsURLConnection) newsApiEndPoint.openConnection();
            inputStream = myConnection.getInputStream();


            if (inputStream != null)
                result = convertInputStreamToString(myConnection.getInputStream());
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

//    Converts the inputstream from the endpoint and converts response to a string

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    private class HttpAsyncTechTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!" + "You are connected to NEWS API:", Toast.LENGTH_LONG).show();
            try {
                org.json.JSONObject jsonObject = new org.json.JSONObject(result);
                ArrayList<TechnologyRelated> technologyRelatedArrayList = new ArrayList<>();
                String sources = jsonObject.getString("articles");
                Log.v("connection sources", sources);
                Toast.makeText(getBaseContext(), "Welcome to sources by tecnology!" + "You are connected to NEWS API:", Toast.LENGTH_LONG).show();

                HashMap<String, String> categoryHashMap = new HashMap<>();
                JSONArray jsonArray = new JSONArray(sources);

                for (int i = 0; i < jsonArray.length(); i++) {
                    org.json.JSONObject jObject = jsonArray.getJSONObject(i);
                    TITLE = jObject.getString("title");
                    DESCRIPTION = jObject.getString("description");

                    Log.v("results name:", TITLE);
                    Log.v("results DESCRIPTION:", DESCRIPTION);

                    populateRecyclerView(TITLE, DESCRIPTION);

                }


                mTxtDisplay.setText(TITLE);
                getResponse.setText(DESCRIPTION);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.v("connection", result);
//            mTxtDisplay.setText(result);
        }
    }


    //    Passes through the data we want to display in recyclerView
    public void populateRecyclerView(String title, String Description) {
        TechnologyRelated technologyRelated = new TechnologyRelated(TITLE, DESCRIPTION);
        technologyRelatedArrayList.add(technologyRelated);


    }

    //check if there is access to the internet
    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
}
