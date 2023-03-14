package com.example.testrapidapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Main activity has two examples with different api's. The difference is using headers and not using headers
 * @see #apiRequesterWithHeaders()
 * @see #apiRequesterWithoutHeaders()
 */
public class MainActivity extends AppCompatActivity {

    //extracts from local.properties, else "".
    String apiKeyForAPI = getApiKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiRequesterWithHeaders();
    }

    /**
     * Method that will use rapidapi to get content. log.d will show what is being extracted when running program.
     * @throws NullPointerException if url is null.
     */
    private void apiRequesterWithHeaders(){
        RequestQueue queue = Volley.newRequestQueue(this);
        //do not need this (only used this to explain its from field variable).
        String url = "https://rapidapi.com/"; //TODO: need to add a meaningful request otherwise this will return the whole dom!
        String apiKey = this.apiKeyForAPI;
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // response
                    Log.d("Response", response);
                    final TextView textView = (TextView) findViewById(R.id.motivationalQuote);
                    textView.setText(response);
                },
                error -> {
                    Log.d("ERROR","error => "+error.toString());
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", apiKey);
                params.put("X-RapidAPI-Host", "exercises2.p.rapidapi.com");

                return params;
            }
        };
        queue.add(getRequest);
        return;
    }

    /**
     * uses zenquotes to extract random quotes.
     * @throws JSONException if response is null.
     */
    private void renderQuotes(){


        // https://www.youtube.com/watch?v=xPi-z3nOcn8
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://zenquotes.io/api/quotes";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            String quote = "";
            try {
//                textView.setText("Response is: " + response.getJSONObject(0));
                JSONObject allQuoteData = response.getJSONObject(0);
                quote = " \" " + allQuoteData.getString("q") + "\"\n " + " - " + allQuoteData.getString("a");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final TextView textView = (TextView) findViewById(R.id.motivationalQuote);
            textView.setText(quote);
        }, error -> Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show());

        queue.add(request);
    }


    /**
     * Extracts api key from local.properties.
     * @return string - api value from local.properties otherwise empty string.
     */
    public String getApiKey(){
        Properties props = new Properties();
        String apiKey = "";
        try {
            InputStream inputStream = new FileInputStream(new File("local.properties"));
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        apiKey = props.getProperty("api.key");
        return apiKey;
    }
}