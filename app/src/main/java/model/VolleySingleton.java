package model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by arvid.quarshie on 5/7/2017.
 * <p>
 * A singleton for the VolleyLibrary , allows us to use it through out the project when
 * using the volleyLibrary
 */

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context context;


    private VolleySingleton(Context mcontext) {
        context = mcontext;
        requestQueue = getRequestQueue();

    }

    public RequestQueue getRequestQueue() {

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);

        }
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        requestQueue.add(request);

    }

}