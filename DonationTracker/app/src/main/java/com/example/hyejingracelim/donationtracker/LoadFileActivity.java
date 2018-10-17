package com.example.hyejingracelim.donationtracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.List;

public class LoadFileActivity extends AppCompatActivity {
    List<String[]> loadedData;
    String key, locationName, locationType, address, city, state, latitude, longitude, zipCode, phone, website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadfile);
        readFile.readData("LocationData.csv");
    }

    public void loadClick(View view) {
        Button button = (Button) view;
        button.setVisibility(View.GONE);
        loadedData = readFile.getData();
        HashMap<String, Integer> loaded = new HashMap<>();
        for (int i = 0; i < loadedData.size(); i++) {
            for (int j = 0; j < 11; j++) {
                switch (j) {
                    case 0:
                        key = loadedData.get(i).toString();
                        loaded.put(key, i);
                        break;
                    case 1:
                        locationName = loadedData.get(i).toString();
                        loaded.put(locationName, i);
                        break;
                    case 2:
                        latitude = loadedData.get(i).toString();
                        loaded.put(latitude, i);
                        break;
                    case 3:
                        longitude = loadedData.get(i).toString();
                        loaded.put(longitude, i);
                        break;
                    case 4:
                        address = loadedData.get(i).toString();
                        loaded.put(address, i);
                        break;
                    case 5:
                        city = loadedData.get(i).toString();
                        loaded.put(city, i);
                        break;
                    case 6:
                        state = loadedData.get(i).toString();
                        loaded.put(state, i);
                        break;
                    case 7:
                        zipCode = loadedData.get(i).toString();
                        loaded.put(zipCode, i);
                        break;
                    case 8:
                        locationType = loadedData.get(i).toString();
                        loaded.put(locationType, i);
                        break;
                    case 9:
                        phone = loadedData.get(i).toString();
                        loaded.put(phone, i);
                        break;
                    case 10:
                        website = loadedData.get(i).toString();
                        loaded.put(website, i);
                        break;
                    default:
                        break;
                }
            }
        }

    }
}
