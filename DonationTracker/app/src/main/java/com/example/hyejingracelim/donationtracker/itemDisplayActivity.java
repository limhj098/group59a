package com.example.hyejingracelim.donationtracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class itemDisplayActivity extends AppCompatActivity implements View.OnClickListener{

    private String myTime;
    private String name;
    private String shortDescription;
    private String fullDescription;
    private String location;
    private String catagory;
    private ListView lv;
    private TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_display_page);
        try {
            tv = (TextView) findViewById(R.id.itemAttributes);
            lv = (ListView) findViewById(R.id.itemList);
            lv.setClickable(true);

            FileInputStream fis = openFileInput("itemInfo");
            ObjectInputStream ois = new ObjectInputStream(fis);
            final HashMap<String, Item> itemInfo = (HashMap<String, Item>) ois.readObject();

            ois.close();
            fis.close();
            Set<String> itemSet = itemInfo.keySet();
            List<String> itemList = new ArrayList<>(itemSet);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    itemList
            );
            lv.setAdapter(arrayAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String g = (String) lv.getItemAtPosition(position);
                    Item currentItem = itemInfo.get(g);
                    tv.setText(currentItem.toString());
                }
            });
        }
        catch(Exception e) {
        }
        Button buttonAddMore = (Button) findViewById(R.id.add_item);
        buttonAddMore.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            myTime = extras.getString("time");
            name = extras.getString("name");
            shortDescription = extras.getString("shortDescription");
            fullDescription = extras.getString("fullDescription");
            location = extras.getString("location");
            catagory = extras.getString("catagory");
        }

    }

    public void onClick(View view){
        submitClick();
    }

    public void submitClick() {
        Intent i = new Intent(this, DataEntryActivity.class);
        startActivity(i);
        finish();
    }


}
