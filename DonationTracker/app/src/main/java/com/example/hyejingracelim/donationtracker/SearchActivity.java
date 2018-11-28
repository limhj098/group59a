package com.example.hyejingracelim.donationtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @version 11/8/18
 * @author group59a
 * Class that differentiates the different users that can log in
 * Stores information about different users
 */
public class SearchActivity extends AppCompatActivity {

    private Button locationButton;
    private Button categoryButton;
    private Button itemNameButton;
    private Spinner categorySpinner;
    private Spinner locationSpinner;
    private EditText itemNameSearch;

    private TextView results;

    /**
     *
     * @param savedInstanceState The saved instance state of the Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        final Button locationButton = findViewById(R.id.button3);
        final Button categoryButton = findViewById(R.id.button4);
        final Button itemNameButton = findViewById(R.id.button5);

        itemNameSearch = findViewById(R.id.itemName);

        locationSpinner = findViewById(R.id.location);

        ArrayAdapter<String> adapter = new ArrayAdapter
                (this,android.R.layout.simple_spinner_item, User.locations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        categorySpinner = findViewById(R.id.category);

        ArrayAdapter<String> adapter2 = new ArrayAdapter
                (this,android.R.layout.simple_spinner_item, User.categories);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter2);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    FileInputStream fis = openFileInput("itemInfo");
                    ObjectInputStream ois = new ObjectInputStream(fis);

                    final Map<String, Item> itemInfo = (HashMap<String, Item>) ois.readObject();
                    ois.close();
                    fis.close();
                    String location_data = locationSpinner.getSelectedItem().toString();
                    final Map<String, Item> filteredMap = new HashMap<>();
                    for (Map.Entry<String,Item> entry : itemInfo.entrySet()){
                        if (entry.getValue().getLocation().equals(location_data)) {
                            filteredMap.put(entry.getKey(),entry.getValue());
                        }
                    }
                    final TextView tv1 = findViewById(R.id.textView4);
                    final ListView lv1 = findViewById(R.id.searchResult);
                    lv1.setClickable(true);
                    ois.close();
                    fis.close();
                    Set<String> itemSet = filteredMap.keySet();
                    List<String> itemList = new ArrayList<>(itemSet);
                    ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(
                            getBaseContext(),
                            android.R.layout.simple_list_item_1,
                            itemList
                    );
                    lv1.setAdapter(arrayAdapter1);
                    lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick
                                (AdapterView<?> parent, View view, int position, long id) {
                            String g = (String) lv1.getItemAtPosition(position);
                            Item currentItem = filteredMap.get(g);
                            assert currentItem != null;
                            tv1.setText(currentItem.toString());
                        }
                    });
                }
                catch(Exception ignored){}

                //  assertEquals(expectedMap, filteredMap);
            }


        });

        categoryButton.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v View v that is used when the button is clicked.
             */
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput("itemInfo");
                    ObjectInputStream ois = new ObjectInputStream(fis);

                    final Map<String, Item> itemInfo = (HashMap<String, Item>) ois.readObject();
                    ois.close();
                    fis.close();
                    String category_data = categorySpinner.getSelectedItem().toString();
                    final Map<String, Item> filteredMap2 = new HashMap<>();
                    for (Map.Entry<String,Item> entry : itemInfo.entrySet()){
                        if (entry.getValue().getCategory().equals(category_data)) {
                            filteredMap2.put(entry.getKey(),entry.getValue());
                        }
                    }
                    final TextView tv1 = findViewById(R.id.textView4);
                    final ListView lv1 = findViewById(R.id.searchResult);
                    lv1.setClickable(true);
                    ois.close();
                    fis.close();
                    Set<String> itemSet = filteredMap2.keySet();
                    List<String> itemList = new ArrayList<>(itemSet);
                    ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(
                            getBaseContext(),
                            android.R.layout.simple_list_item_1,
                            itemList
                    );
                    lv1.setAdapter(arrayAdapter1);
                    lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        /**
                         *
                         * @param parent AdapterView<?> parent
                         * @param view View class used.
                         * @param position An int of the position.
                         * @param id A long of the position.
                         */
                        @Override
                        public void onItemClick
                        (AdapterView<?> parent, View view, int position, long id) {
                            String g = (String) lv1.getItemAtPosition(position);
                            Item currentItem = filteredMap2.get(g);
                            assert currentItem != null;
                            tv1.setText(currentItem.toString());
                        }
                    });
                }
                catch(Exception ignored){}
                //  assertEquals(expectedMap, filteredMap);

            }

        });
        itemNameButton.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v View that is used when button is clicked.
             */
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput("itemInfo");
                    ObjectInputStream ois = new ObjectInputStream(fis);

                    final Map<String, Item> itemInfo = (HashMap<String, Item>) ois.readObject();
                    ois.close();
                    fis.close();
                    String name_data = itemNameSearch.toString();
                    final Map<String, Item> filteredMap3 = new HashMap<>();
                    for (Map.Entry<String,Item> entry : itemInfo.entrySet()){
                        if (entry.getKey().equalsIgnoreCase(name_data)) {
                            filteredMap3.put(entry.getKey(),entry.getValue());
                        }
                    }
                    final TextView tv1 = findViewById(R.id.textView4);
                    final ListView lv1 = findViewById(R.id.searchResult);
                    lv1.setClickable(true);
                    ois.close();
                    fis.close();
                    Set<String> itemSet = filteredMap3.keySet();
                    List<String> itemList = new ArrayList<>(itemSet);
                    ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(
                            getBaseContext(),
                            android.R.layout.simple_list_item_1,
                            itemList
                    );
                    lv1.setAdapter(arrayAdapter1);
                    lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        /**
                         *
                         * @param parent AdapterView<?> parent
                         * @param view View class used.
                         * @param position An int of the position.
                         * @param id A long of the position.
                         */
                        @Override
                        public void onItemClick
                        (AdapterView<?> parent, View view, int position, long id) {
                            String g = (String) lv1.getItemAtPosition(position);
                            Item currentItem = filteredMap3.get(g);
                            assert currentItem != null;
                            tv1.setText(currentItem.toString());
                        }
                    });
                }
                catch(Exception ignored){}
                //  assertEquals(expectedMap, filteredMap);
            }

        });
    }
}
