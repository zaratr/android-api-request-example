package com.example.testrapidapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String valueTempt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiRequester();
    }

    public void apiRequester(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://rapidapi.com/";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // response
                    Log.d("Response", response);
                    final TextView textView = (TextView) findViewById(R.id.motivationalQuote);
                    textView.setText(response);
                },
                error -> {
                    // TODO Auto-generated method stub
                    Log.d("ERROR","error => "+error.toString());
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", "0c2becdfa7mshb9a3c113775cbdep163e0djsn8543d4ba6d1a");
                params.put("X-RapidAPI-Host", "exercises2.p.rapidapi.com");

                return params;
            }
        };
        queue.add(getRequest);
        return;
    }
}