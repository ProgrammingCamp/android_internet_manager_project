package com.programmingcamp.internetmanager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCheckWiFiConnection, btnCheckDataConnection, btnLoadWebApiData, btnSaveDataToWebApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheckWiFiConnection = (Button) findViewById(R.id.btnCheckWiFiConnection);
        btnCheckDataConnection = (Button) findViewById(R.id.btnCheckDataConnection);
        btnLoadWebApiData = (Button) findViewById(R.id.btnLoadWebApiData);
        btnSaveDataToWebApi = (Button) findViewById(R.id.btnSaveDataToWebApi);

        btnCheckWiFiConnection.setOnClickListener(this);
        btnCheckDataConnection.setOnClickListener(this);
        btnLoadWebApiData.setOnClickListener(this);
        btnSaveDataToWebApi.setOnClickListener(this);
    }

    @Override
    public void onClick(View button) {
        if(button.getId() == btnCheckWiFiConnection.getId()){
            ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            //If connectivity object is not null
            if (connectivity != null) {
                //Get network info - WIFI internet access
                NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (info != null) {
                    //Look for whether device is currently connected to WIFI network
                    if (info.isConnected()) {
                        Toast.makeText(this, "WiFi Available", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            }

            Toast.makeText(this, "WiFi NOT Available", Toast.LENGTH_LONG).show();
        }
        else if(button.getId() == btnCheckDataConnection.getId()){
            ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            //If connectivity object is not null
            if (connectivity != null) {
                //Get network info - WIFI internet access
                NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (info != null) {
                    //Look for whether device is currently connected to WIFI network
                    if (info.isConnected()) {
                        Toast.makeText(this, "Data Connection Available", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            }

            Toast.makeText(this, "Data Connection NOT Available", Toast.LENGTH_LONG).show();
        }
        else if(button.getId() == btnLoadWebApiData.getId()){
            Intent intent = new Intent(this, LoadWebApiDataActivity.class);
            startActivity(intent);
        }
        else if(button.getId() == btnSaveDataToWebApi.getId()){
            Intent intent = new Intent(this, SaveDataToWebApiActivity.class);
            startActivity(intent);
        }
    }
}
