package com.programmingcamp.internetmanager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class LoadWebApiDataActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLoadWebApiData;
    EditText txtWebApiData;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_web_api_data);

        context = this;

        txtWebApiData = (EditText) findViewById(R.id.txtWebApiData);
        btnLoadWebApiData = (Button) findViewById(R.id.btnLoadWebApiData);

        btnLoadWebApiData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        final String url = "http://services.groupkt.com/state/get/IND/all";
        RequestQueue queue = Volley.newRequestQueue(context);

        try{
            JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    String countryName = "-";

                    if (response.length() > 0) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                countryName = countryName + " , " + response.getJSONObject(i).getString("name");
                            }

                            txtWebApiData.setText(countryName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        Toast.makeText(context, "No record found", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Error occured", Toast.LENGTH_LONG).show();
                }
            });

            // add it to the RequestQueue
            queue.add(req);
        }
        catch(Exception ex){
            Toast.makeText(context, "Error occured", Toast.LENGTH_LONG).show();
        }
    }
}
