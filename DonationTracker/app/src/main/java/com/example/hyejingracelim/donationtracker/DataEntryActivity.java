package com.example.hyejingracelim.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectOutput;

import java.util.HashMap;
import java.util.Map;

/**
 * This class Contains a Data entry method for information into the application
 */
public class DataEntryActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText timeField;
    private EditText nameField;
    private EditText shortDescriptionField;
    private EditText fullDescriptionField;
    private Spinner categorySpinner;
    private Spinner locationSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_entry);

        timeField = findViewById(R.id.time);
        nameField = findViewById(R.id.name);
        shortDescriptionField = findViewById(R.id.short_description);
        fullDescriptionField = findViewById(R.id.full_description);

        locationSpinner = findViewById(R.id.location_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter
                (this,android.R.layout.simple_spinner_item, User.locations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        categorySpinner = findViewById(R.id.category_spinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter
                (this,android.R.layout.simple_spinner_item, User.categories);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter2);


        Button buttonSubmit = findViewById(R.id.submit);
        buttonSubmit.setOnClickListener(this);

        //     Button buttonCancel = (Button) findViewById(R.id.cancel);
        //   buttonCancel.setOnClickListener();
    }

    private void submitClick() {
        String time;
        String name;
        String shortDescription;
        String fullDescription;
        String location_data;
        String category_data;

        if (timeField.getText() != null) {
            time = timeField.getText().toString();
        }
        else {
            time = "not set";
        }
        if (nameField.getText() != null) {
            name = nameField.getText().toString();
        }
        else {
            name = "not set";
        }
        if (shortDescriptionField.getText() != null) {
            shortDescription = shortDescriptionField.getText().toString();
        }
        else {
            shortDescription = "not set";
        }
        if (fullDescriptionField.getText() != null) {
            fullDescription = fullDescriptionField.getText().toString();
        }
        else {
            fullDescription = "not set";
        }
        location_data = locationSpinner.getSelectedItem().toString();
        category_data = categorySpinner.getSelectedItem().toString();

        Item a = new Item();
        a.setDonationTime(time);
        a.setLocation(location_data);
        a.setCategory(category_data);
        a.setLongDescription(fullDescription);
        a.setShortDescription(shortDescription);
        try {
            // Creates a file in the primary external storage space of the
            // If the file does not exists, it is created.
            File testFile = new File(this.getExternalFilesDir(null), "itemInfo");
            if (!testFile.exists()) {
                testFile.createNewFile(); //need this to create a running list
                FileOutputStream ofi = openFileOutput("itemInfo",0);
                ObjectOutput oos = new ObjectOutputStream(ofi);
                Map<String, Item> test = new HashMap<>();
                test.put(name, a);
                oos.writeObject(test);
                oos.close();
                ofi.close();
            }
            //adds to file
            else {
                FileInputStream fis = openFileInput("itemInfo");
                ObjectInputStream ois = new ObjectInputStream(fis);
                Map<String, Item> itemInfo = (HashMap<String, Item>) ois.readObject();
                ois.close();
                fis.close();

                FileOutputStream ofi = openFileOutput("itemInfo",0);
                ObjectOutput oos = new ObjectOutputStream(ofi);
                //Map<String, Item> test = itemInfo;
                itemInfo.put(name, a);
                oos.writeObject(itemInfo);
                oos.close();
                ofi.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent i = new Intent(this,itemDisplayActivity.class);
        startActivity(i);
        finish();

    }

    @Override
    public void onClick(View view){
        submitClick();
    }

    /**
     * This class creates an intent that leads back to locations activity when clicked
     * @param view parameter that takes in view
     */
    public void cancelClick(View view) {
        Intent i2 = new Intent(this, LocationsActivity.class);
        startActivity(i2);
        finish();
    }


}
