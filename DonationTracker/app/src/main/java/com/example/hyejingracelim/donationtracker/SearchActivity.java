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

        locationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Item item = new Item();
                String location_data = locationSpinner.getSelectedItem().toString();
                Map<String, Integer> filteredMap = filterByValue(itemInfo, item.getLocation() -> item.getLocation().equals(location_data));

                TextView tv = (TextView) findViewById(R.id.textView4);
                ListView lv = (ListView) findViewById(R.id.searchResult);
                lv.setClickable(true);

                FileInputStream fis = openFileInput("filteredMap");
                ObjectInputStream ois = new ObjectInputStream(fis);
                final HashMap<String, Item> filteredMap = (HashMap<String, Item>) ois.readObject();

                ois.close();
                fis.close();

                Set<String> itemSet = filteredMap.keySet();
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
                        Item currentItem = filteredMap.get(g);
                        tv.setText(currentItem.toString());
                    }
                });

              //  assertEquals(expectedMap, filteredMap);
               // catagory_data = catagorySpinner.getSelectedItem().toString();

            }});




    }
}
