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
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchActivity extends AppCompatActivity {

    private Button locationButton;
    private Button catagoryButton;
    private Button itemNameButton;
    private Spinner catagorySpinner;
    private Spinner locationSpinner;
    private EditText itemNameSearch;

    private TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        final Button locationButton = (Button) findViewById(R.id.button3);
        final Button catagoryButton = (Button) findViewById(R.id.button4);
        final Button itemNameButton = (Button) findViewById(R.id.button5);

        itemNameSearch = (EditText) findViewById(R.id.itemName);

        locationSpinner = (Spinner) findViewById(R.id.location);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.locations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        catagorySpinner = (Spinner) findViewById(R.id.catagory);
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.catagories);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catagorySpinner.setAdapter(adapter2);
        locationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                try {
                    FileInputStream fis = openFileInput("itemInfo");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    final HashMap<String, Item> itemInfo = (HashMap<String, Item>) ois.readObject();
                    ois.close();
                    fis.close();
                    String location_data = locationSpinner.getSelectedItem().toString();
                    final HashMap<String, Item> filteredMap = new HashMap<>();
                    for (Map.Entry<String,Item> entry : itemInfo.entrySet()){
                        if (entry.getValue().location.equals(location_data)) {
                            filteredMap.put(entry.getKey(),entry.getValue());
                        }
                    }
                    final TextView tv1 = (TextView) findViewById(R.id.textView4);
                    final ListView lv1 = (ListView) findViewById(R.id.searchResult);
                    lv1.setClickable(true);
                    ois.close();
                    fis.close();
                    Set<String> itemSet = filteredMap.keySet();
                    List<String> itemList = new ArrayList<>(itemSet);
                    ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(
                            getBaseContext(),
                            android.R.layout.simple_list_item_1,
                            itemList
                    );
                    lv1.setAdapter(arrayAdapter1);
                    lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String g = (String) lv1.getItemAtPosition(position);
                            Item currentItem = filteredMap.get(g);
                            tv1.setText(currentItem.toString());
                        }
                    });
                }
                catch(Exception e){};

              //  assertEquals(expectedMap, filteredMap);
               // catagory_data = catagorySpinner.getSelectedItem().toString();
            }


        });
        catagoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput("itemInfo");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    final HashMap<String, Item> itemInfo = (HashMap<String, Item>) ois.readObject();
                    ois.close();
                    fis.close();
                    String catagory_data = catagorySpinner.getSelectedItem().toString();
                    final HashMap<String, Item> filteredMap2 = new HashMap<>();
                    for (Map.Entry<String,Item> entry : itemInfo.entrySet()){
                        if (entry.getValue().catagory.equals(catagory_data)) {
                            filteredMap2.put(entry.getKey(),entry.getValue());
                        }
                    }
                    final TextView tv1 = (TextView) findViewById(R.id.textView4);
                    final ListView lv1 = (ListView) findViewById(R.id.searchResult);
                    lv1.setClickable(true);
                    ois.close();
                    fis.close();
                    Set<String> itemSet = filteredMap2.keySet();
                    List<String> itemList = new ArrayList<>(itemSet);
                    ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(
                            getBaseContext(),
                            android.R.layout.simple_list_item_1,
                            itemList
                    );
                    lv1.setAdapter(arrayAdapter1);
                    lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String g = (String) lv1.getItemAtPosition(position);
                            Item currentItem = filteredMap2.get(g);
                            tv1.setText(currentItem.toString());
                        }
                    });
                }
                catch(Exception e){};
                //  assertEquals(expectedMap, filteredMap);
                // catagory_data = catagorySpinner.getSelectedItem().toString();
            }

        });
        itemNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput("itemInfo");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    final HashMap<String, Item> itemInfo = (HashMap<String, Item>) ois.readObject();
                    ois.close();
                    fis.close();
                    String name_data = itemNameSearch.toString();
                    final HashMap<String, Item> filteredMap3 = new HashMap<>();
                    for (Map.Entry<String,Item> entry : itemInfo.entrySet()){
                        if (entry.getKey().equalsIgnoreCase(name_data)) {
                            filteredMap3.put(entry.getKey(),entry.getValue());
                        }
                    }
                    final TextView tv1 = (TextView) findViewById(R.id.textView4);
                    final ListView lv1 = (ListView) findViewById(R.id.searchResult);
                    lv1.setClickable(true);
                    ois.close();
                    fis.close();
                    Set<String> itemSet = filteredMap3.keySet();
                    List<String> itemList = new ArrayList<>(itemSet);
                    ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(
                            getBaseContext(),
                            android.R.layout.simple_list_item_1,
                            itemList
                    );
                    lv1.setAdapter(arrayAdapter1);
                    lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String g = (String) lv1.getItemAtPosition(position);
                            Item currentItem = filteredMap3.get(g);
                            tv1.setText(currentItem.toString());
                        }
                    });
                }
                catch(Exception e){};
                //  assertEquals(expectedMap, filteredMap);
                // catagory_data = catagorySpinner.getSelectedItem().toString();
            }

        });
    }
}
