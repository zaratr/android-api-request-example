package com.example.testrapidapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.*;

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
 */
public class MainActivity extends AppCompatActivity {

    //extracts from local.properties, else "".
    //String apiKeyForAPI = getApiKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiRequesterWithHeaders();
        //apiRequesterWithoutHeaders();
    }

    /**
     * Method that will use rapidapi to get content. log.d will show what is being extracted when running program.
     * @throws NullPointerException if url is null.
     */
    private void apiRequesterWithHeaders() {
        // run the API request in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String apiUrl = "https://exerciseapi3.p.rapidapi.com/search/muscles/";
                String apiKey ="";// <"replace me with yourAPI key" >

                Request request = new Request.Builder()
                        .url(apiUrl)
                        .get()
                        .addHeader("X-RapidAPI-Key", apiKey)
                        .addHeader("X-RapidAPI-Host", "exerciseapi3.p.rapidapi.com")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String responseBody = response.body().string();

                    // update the UI on the main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final TextView textView = findViewById(R.id.motivationalQuote);
                            textView.setText(responseBody);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * uses zenquotes to extract random quotes. make sure okhttp3 is not being used to use this volley example.
     * @throws JSONException if response is null.
     */

    /*
    private void  apiRequesterWithoutHeaders(){


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

     */

    /**
     * Extracts api key from local.properties.
     * @return string - api value from local.properties otherwise empty string.
     */
    /*
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

     */
}