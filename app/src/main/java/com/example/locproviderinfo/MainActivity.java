package com.example.locproviderinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView mtextInfo;
    LocationManager locManager;
    List<String> locProviderlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtextInfo = findViewById(R.id.textInfo);
        locManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        locProviderlist = locManager.getAllProviders();
        mtextInfo.setText("");
        for(String locprovider: locProviderlist){
            mtextInfo.append("Loc Provider: " + locprovider + "\n");
            mtextInfo.append("Enabled: " + locManager.isProviderEnabled(locprovider) + "\n \n");
        }
    }
}