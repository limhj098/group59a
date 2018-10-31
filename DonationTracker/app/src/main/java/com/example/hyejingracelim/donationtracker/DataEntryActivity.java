package com.example.hyejingracelim.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DataEntryActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText timeField;
    private EditText nameField;
    private EditText shortDescriptionField;
    private EditText fullDescriptionField;
    private Spinner catagorySpinner;
    private Spinner locationSpinner;
    private Button buttonSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_entry);

        timeField = (EditText) findViewById(R.id.time);
        nameField = (EditText) findViewById(R.id.name);
        shortDescriptionField = (EditText) findViewById(R.id.short_description);
        fullDescriptionField = (EditText) findViewById(R.id.full_description);

        locationSpinner = (Spinner) findViewById(R.id.location_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.locations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        catagorySpinner = (Spinner) findViewById(R.id.catagory_spinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.catagories);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catagorySpinner.setAdapter(adapter2);


        buttonSubmit = (Button) findViewById(R.id.submit);
        buttonSubmit.setOnClickListener(this);

        //     Button buttonCancel = (Button) findViewById(R.id.cancel);
        //   buttonCancel.setOnClickListener();
    }

    public void submitClick() {

        //Add some way to store the data from the text fields
        String time;
        String name;
        String shortDescript;
        String fullDescript;
        String location_data;
        String catagory_data;

        if (timeField.getText() != null) {
            time = timeField.getText().toString();
        }
        else time = "not set";
        if (nameField.getText() != null) {
            name = nameField.getText().toString().concat(" : ").concat(shortDescriptionField.getText().toString());
        }
        else name = "not set";
        if (shortDescriptionField.getText() != null) {
            shortDescript = shortDescriptionField.getText().toString();
        }
        else shortDescript = "not set";
        if (fullDescriptionField.getText() != null) {
            fullDescript = fullDescriptionField.getText().toString();
        }
        else fullDescript = "not set";
        location_data = locationSpinner.getSelectedItem().toString();
        catagory_data = catagorySpinner.getSelectedItem().toString();

        //navigate to data load page to display the item that they have entered.
        //will load diff item based on location that is entered into the textfield.
        Item a = new Item();
        a.donationTime = time;
        a.location = location_data;
        a.catagory = catagory_data;
        a.longDescription = fullDescript;
        a.shortDescription = shortDescript;



        try {
            // Creates a file in the primary external storage space of the
            // current application.
            // If the file does not exists, it is created.
            File testFile = new File(this.getExternalFilesDir(null), "itemInfo");
            if (!testFile.exists()) {
                testFile.createNewFile();
                FileOutputStream ofi = openFileOutput("itemInfo",0);
                ObjectOutputStream oos = new ObjectOutputStream(ofi);
                HashMap<String, Item> test = new HashMap<String, Item>();
                test.put(name, a);
                oos.writeObject(test);
                oos.close();
                ofi.close();
            }
            // Adds a line to the file
            else {
                FileInputStream fis = openFileInput("itemInfo");
                ObjectInputStream ois = new ObjectInputStream(fis);
                HashMap<String, Item> itemInfo = (HashMap<String, Item>) ois.readObject();
                ois.close();
                fis.close();

                FileOutputStream ofi = openFileOutput("itemInfo",0);
                ObjectOutputStream oos = new ObjectOutputStream(ofi);
                HashMap<String, Item> test = itemInfo;
                test.put(name, a);
                oos.writeObject(test);
                oos.close();
                ofi.close();
            }
            // Refresh the data so it can seen when the device is plugged in a
            // computer. You may have to unplug and replug the device to see the
            // latest changes. This is not necessary if the user should not modify
            // the files.

        } catch (Exception e) { }
        Intent i = new Intent(this,itemDisplayActivity.class);
        i.putExtra("time", timeField.getText().toString());
        i.putExtra("name", nameField.getText().toString());
        i.putExtra("shortDescription", shortDescriptionField.getText().toString());
        i.putExtra("fullDescription", fullDescriptionField.getText().toString());
        i.putExtra("Location", locationSpinner.getSelectedItem().toString());
        i.putExtra("Catagory", locationSpinner.getSelectedItem().toString());
        startActivity(i);
        finish();
    }

    public void onClick(View view){
        submitClick();
    }

    public void cancelClick(View view) {
        Intent i2 = new Intent(this, LocationsActivity.class);
        startActivity(i2);
        finish();
    }


}
