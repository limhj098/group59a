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
        readFile.readData("LocationData.csv");        //static method call for readFile class
                                                           //to store data from the file stated in parameters
    }

    public void loadClick(View view) {
        Button button = (Button) view;
        button.setVisibility(View.GONE);                   //If load data button is clicked, the button disappears from memory AND from view.
        loadedData = readFile.getData();                   //Retrieves the loaded data from file onto the list
        HashMap<String, Integer> loaded = new HashMap<>();
        for (int i = 0; i < loadedData.size(); i++) {      //There are 12 elements for each Location in the file, so I want to get the correct element
                                                           //for each variable, so I scan through the list making sure I put in the right variable the element of the right place in the List.
            for (int j = 0; j < 11; j++) {
                switch (j) {
                    case 0:
                        key = loadedData.get(i + j).toString();
                        loaded.put(key, i + j);
                        break;
                    case 1:
                        locationName = loadedData.get(i + j).toString();
                        loaded.put(locationName, i + j);
                        break;
                    case 2:
                        latitude = loadedData.get(i + j).toString();
                        loaded.put(latitude, i + j);
                        break;
                    case 3:
                        longitude = loadedData.get(i + j).toString();
                        loaded.put(longitude, i + j);
                        break;
                    case 4:
                        address = loadedData.get(i + j).toString();
                        loaded.put(address, i + j);
                        break;
                    case 5:
                        city = loadedData.get(i + j).toString();
                        loaded.put(city, i + j);
                        break;
                    case 6:
                        state = loadedData.get(i + j).toString();
                        loaded.put(state, i + j);
                        break;
                    case 7:
                        zipCode = loadedData.get(i + j).toString();
                        loaded.put(zipCode, i + j);
                        break;
                    case 8:
                        locationType = loadedData.get(i + j).toString();
                        loaded.put(locationType, i + j);
                        break;
                    case 9:
                        phone = loadedData.get(i + j).toString();
                        loaded.put(phone, i + j);
                        break;
                    case 10:
                        website = loadedData.get(i + j).toString();
                        loaded.put(website, i + j);
                        break;
                    default:
                        break;
                }
            }
        }

    }
}
