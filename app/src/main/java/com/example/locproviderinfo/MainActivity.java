package com.example.locproviderinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView mtextInfo;
    LocationManager locManager;
    List<String> locProviderlist;
    LocationProvider locProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtextInfo = findViewById(R.id.textInfo);
        locManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        locProviderlist = locManager.getAllProviders();
        mtextInfo.setText("");
        for(String locproviderName: locProviderlist){
            mtextInfo.append("Loc Provider: " + locproviderName + "\n");
            mtextInfo.append("Enabled: " + locManager.isProviderEnabled(locproviderName) + "\n \n");

            switch(locproviderName){
                case "gps": locProvider = locManager.getProvider(LocationManager.GPS_PROVIDER);
                    break;
                case "networdk": locProvider = locManager.getProvider(LocationManager.NETWORK_PROVIDER);
                    break;
                case "passive": locProvider = locManager.getProvider(LocationManager.PASSIVE_PROVIDER);
                    break;
            }
            printInfo(locProvider);
        }
    }

    private void printInfo(LocationProvider locProvider) {
        mtextInfo.append("이름: " + locProvider.getName() + "\n");
        mtextInfo.append("현재이용가능여부: " +
                locManager.isProviderEnabled(locProvider.getName()) + "\n");
        mtextInfo.append("위성필요여부: " + locProvider.requiresSatellite() + "\n");
        mtextInfo.append("인터넷접속필요여부: " + locProvider.requiresNetwork() + "\n");
        mtextInfo.append("기지국필요여부: " + locProvider.requiresCell() + "\n");

        mtextInfo.append("고도정보지원여부: " + locProvider.supportsAltitude() + "\n");
        mtextInfo.append("방향정보지원여부: " + locProvider.supportsBearing() + "\n");
        mtextInfo.append("속도정보지원여부: " + locProvider.supportsSpeed() + "\n");

        String accuracy = "";
        switch(locProvider.getAccuracy()) {
            //2.3부터 사용
            case Criteria.ACCURACY_LOW:    accuracy="500m 이상 오차"; break;
            case Criteria.ACCURACY_MEDIUM: accuracy="100~500m 오차"; break;
            case Criteria.ACCURACY_HIGH:   accuracy="0~100m 오차"; break;
        }
        mtextInfo.append("정확성정도: " + accuracy + "\n");

        String power = "";
        switch(locProvider.getPowerRequirement()) {
            case Criteria.POWER_LOW:    power="적게 사용"; break;
            case Criteria.POWER_MEDIUM: power="중간 사용"; break;
            case Criteria.POWER_HIGH:   power="많이 사용"; break;
        }
        mtextInfo.append("전원사용정도: " + power + "\n");

        mtextInfo.append("요금지불여부: " + locProvider.hasMonetaryCost() + "\n");
        mtextInfo.append("\n");
    }
}