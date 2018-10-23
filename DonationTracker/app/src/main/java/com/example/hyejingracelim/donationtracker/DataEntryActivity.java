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

import java.util.Arrays;
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
        if (nameField.getText() != null) {
            name = nameField.getText().toString();
        }
        if (shortDescriptionField.getText() != null) {
            shortDescript = shortDescriptionField.getText().toString();
        }
        if (fullDescriptionField.getText() != null) {
            fullDescript = fullDescriptionField.getText().toString();
        }

        location_data = locationSpinner.getSelectedItem().toString();
        catagory_data = catagorySpinner.getSelectedItem().toString();

        //navigate to data load page to display the item that they have entered.
        //will load diff item based on location that is entered into the textfield.

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


}
