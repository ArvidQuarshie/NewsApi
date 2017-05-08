package com.example.arvidquarshie.newsapiapp;

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

import adapters.ArticlesAdapter;
import adapters.CategoriesAdapter;
import model.Categories;

public class CategoryActivity extends AppCompatActivity {
    private String sourcesApiUrl = "https://newsapi.org/v1/sources?category=technology";
    private String urlApi = " https://newsapi.org/v1/articles?source=abc-news-au&sortBy=top&apiKey=7b59453e5e9848d9aeeb923a1dd581d0";
    public static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private CategoriesAdapter mAdapter;
    private ArticlesAdapter articlesAdapter;
    private TextView getResponse;
    private ImageView image;
    private TextView mTxtDisplay;
    private List<Categories> categoryArrayList = new ArrayList<>();
    List<? extends HashMap<String, String>> categoryList;
    HashMap<String, String> categoryHashMap = new HashMap<>();
    public String NAME, CATEGORY, DESCRIPTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getResponse = (TextView) findViewById(R.id.description3);
        mTxtDisplay = (TextView) findViewById(R.id.title3);
//        image = (ImageView)findViewById(R.id.urlToImage);

        if (isConnected()) {

            new HttpAsyncTechTask().execute(sourcesApiUrl);
        } else {
            Toast.makeText(getBaseContext(), "Error" + "Please check Network Connection", Toast.LENGTH_LONG).show();
        }
        sampleData(NAME, CATEGORY, DESCRIPTION);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new CategoriesAdapter(categoryArrayList);
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
                ArrayList<Categories> categoriesArrayList = new ArrayList<>();
                String sources = jsonObject.getString("sources");
                Log.v("connection sources", sources);
                Toast.makeText(getBaseContext(), "Welcome to sources by category:" + "You are connected to NEWS API:", Toast.LENGTH_LONG).show();

                HashMap<String, String> categoryHashMap = new HashMap<>();
                JSONArray jsonArray = new JSONArray(sources);

                for (int i = 0; i < jsonArray.length(); i++) {
                    org.json.JSONObject jObject = jsonArray.getJSONObject(i);
                    NAME = jObject.getString("name");
                    CATEGORY = jObject.getString("category");
                    DESCRIPTION = jObject.getString("description");

                    Log.v("results name:", NAME);
                    Log.v("results CATEGORY:", CATEGORY);
                    Log.v("results DESCRIPTION:", DESCRIPTION);
                    sampleData(NAME, CATEGORY, DESCRIPTION);

                }


                mTxtDisplay.setText(NAME);
                getResponse.setText(DESCRIPTION);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.v("connection", result);
//            mTxtDisplay.setText(result);
        }
    }

    public void sampleData(String name, String category, String Description) {
        Categories categories = new Categories(NAME, CATEGORY, DESCRIPTION);
        categoryArrayList.add(categories);


    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

}
