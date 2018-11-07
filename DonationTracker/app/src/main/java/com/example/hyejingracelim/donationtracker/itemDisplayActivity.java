package com.example.hyejingracelim.donationtracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;

/**
 * Class Displays Items to Screen
 */
public class itemDisplayActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lv;
    private TextView tv;


    /**
     * Displays the Item on the screen on the creation of this activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_display_page);
        try {
            tv = findViewById(R.id.itemAttributes);
            lv = findViewById(R.id.itemList);
            lv.setClickable(true);

            FileInputStream fis = openFileInput("itemInfo");
            ObjectInputStream ois = new ObjectInputStream(fis);


            final Map<String, Item> itemInfo = (HashMap<String, Item>) ois.readObject();

            ois.close();
            fis.close();
            Set<String> itemSet = itemInfo.keySet();
            List<String> itemList = new ArrayList<>(itemSet);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                    //Cant change because ArrayAdapter takes in generics while List does not
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
                    if (currentItem != null) {
                        tv.setText(currentItem.toString());
                    }
                }
            });
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        Button buttonAddMore = findViewById(R.id.add_item);
        buttonAddMore.setOnClickListener(this);
    }

    /**
     * On click of button, run submit click.
     * @param view
     */
    @Override
    public void onClick(View view){
        submitClick();
    }

    /**
     * Clicking submit will start a new intent to DataEntryActivity.
     */
    private void submitClick() {
        Intent i = new Intent(this, DataEntryActivity.class);
        startActivity(i);
        finish();
    }


}
