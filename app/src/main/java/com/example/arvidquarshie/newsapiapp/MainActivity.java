package com.example.arvidquarshie.newsapiapp;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private Button getResponse;
    private TextView mTxtDisplay;
    private TextView title;
    private TextView Description;
    private String urlApi = " https://newsapi.org/v1/articles?source=abc-news-au&sortBy=top&apiKey=7b59453e5e9848d9aeeb923a1dd581d0";
    public static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ArticlesAdapter mAdapter;
    private ArticlesAdapter articlesAdapter;

    private List<Article> articleArrayList = new ArrayList<>();
    List<? extends HashMap<String, String>> articleList;
    HashMap<String, String> articleHashMap = new HashMap<>();
    public String TITLE , DESCRIPTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.technology_layout);
        final TextView mTxtDisplay;


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

//        getResponse = (Button) findViewById(R.id.getResponse);
//        mTxtDisplay = (TextView) findViewById(R.id.response);
//        title = (TextView) findViewById(R.id.title);
//        Description = (TextView) findViewById(R.id.description);
        mAdapter = new ArticlesAdapter(articleArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        sampleData(TITLE,DESCRIPTION);
// check if you are connected or not

//        getResponse.setBackgroundColor(0xFF00CC00);
//        mTxtDisplay.setText("You are connected");


        // call AsynTask to perform network operation on separate thread
        new HttpAsyncTask().execute(urlApi);
//        Description.setText(DESCRIPTION);
//        title.setText(TITLE);



    }

    public void sampleData(String title ,String Description) {
        Article article = new Article(TITLE, DESCRIPTION, "");
        articleArrayList.add(article);

        article = new Article(TITLE, DESCRIPTION, "");
        articleArrayList.add(article);




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


    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public interface ClickListener {
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!" + result, Toast.LENGTH_LONG).show();
            try {
                org.json.JSONObject jsonObject = new org.json.JSONObject(result);
                ArrayList<Article> articleArrayList = new ArrayList<>();
                String articles = jsonObject.getString("articles");
                Log.v("connection articles", articles);
                Toast.makeText(getBaseContext(), "Articles!" + articles, Toast.LENGTH_LONG).show();

                HashMap<String, String> articleHashMap = new HashMap<>();
                JSONArray jsonArray = new JSONArray(articles);

                for (int i = 0; i < jsonArray.length(); i++) {
                    org.json.JSONObject jObject = jsonArray.getJSONObject(i);
                     TITLE = jObject.getString("title");
                     DESCRIPTION = jObject.getString("description");
                    String URL = jObject.getString("url");
                    String URL_IMAGE = jObject.getString("urlToImage");

                    Log.v("results TITLE:", TITLE);
                    Log.v("results DESCRIPTION:", DESCRIPTION);
                    Log.v("results URL:", URL);
                    Log.v("results URL_IMAGE:", URL_IMAGE);

                    articleHashMap.put("TITLE",TITLE);
                    articleHashMap.put("DESCRIPTION",DESCRIPTION);
                    articleHashMap.put("URL",URL);





                }

                sampleData(TITLE,DESCRIPTION);


//                title.setText(TITLE);
//                Description.setText(DESCRIPTION);

                Log.v("articleHashMap:", articleArrayList.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.v("connection", result);
//            mTxtDisplay.setText(result);
        }
    }
}


