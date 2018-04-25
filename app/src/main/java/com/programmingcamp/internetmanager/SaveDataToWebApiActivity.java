package com.programmingcamp.internetmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SaveDataToWebApiActivity extends AppCompatActivity implements View.OnClickListener {

    TextView lblFullname, lblMotherName, lblAge;
    EditText txtFullname, txtMotherName, txtAge;
    Button btnSaveDataToWebApi;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data_to_web_api);

        context = this;
        lblFullname = (TextView) findViewById(R.id.lblFullname);
        lblMotherName = (TextView) findViewById(R.id.lblMotherName);
        lblAge = (TextView) findViewById(R.id.lblAge);

        txtFullname = (EditText) findViewById(R.id.txtFullname);
        txtMotherName = (EditText) findViewById(R.id.txtMotherName);
        txtAge = (EditText) findViewById(R.id.txtAge);

        btnSaveDataToWebApi = (Button) findViewById(R.id.btnSaveDataToWebApi);
        btnSaveDataToWebApi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RequestQueue queue = Volley.newRequestQueue(context);  // this = context
        final String url = "http://www.sampleWebApi.com/api/Membership/SaveMember";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Saved Successfully", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Network error, please try again", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Fullname", txtFullname.getText().toString());
                params.put("MotherName", txtMotherName.getText().toString());
                params.put("Age", txtAge.getText().toString());

                return params;
            }
        };
        queue.add(postRequest);
    }
}
