package com.example.hyejingracelim.donationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class LocationsActivity extends AppCompatActivity implements ListFrag.itemSelected {

    static boolean loadData = false;
    static ArrayList<String[]>  allData = new ArrayList<String[]>(); // for inspection
    //static ArrayList<String[]>  allData ; // for inspection
    //static ArrayList<String>  list = null; // for inspection
    static ArrayList<String>  list ; // for inspection
    static String []  detailedList;

    /**
     *
     * @param savedInstanceState , the way this method works
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
    }

//

    /**
     *
     * @param view , has to be fed in here for the button to work
     */
    public void readData(View view) {
        loadData =true;
        //list =new ArrayList<String>(); // for inspection
        list =new ArrayList<>(); // for inspection

        ArrayList<String[]>  allData = readCSV();
        Log.wtf("Moose"," entered logout method");
        Log.d("Moose",Arrays.toString(allData.get(0)));
        String ToastMsg = "Data have been loaded successfully";
        // The only way to use the show method
        Toast.makeText(getApplicationContext(),ToastMsg,Toast.LENGTH_LONG).show();


        list.clear();

        for (String[] str: allData){
            list.add(str[1]+" \n("+str[8]+")");

        }
        list.remove(0);

        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }

    /**
     *
     * @return , returns a String arrayList
     * method changed to private for inspection
     */
    private  ArrayList<String[]> readCSV(){

        //allData = new ArrayList<String[]>(); // for inspection
        //allData = new ArrayList<>(); // for inspection
        ArrayList<String[]> allData2 = new ArrayList<>(); // for inspection


        // Read the raw csv file
        // The only way to use the input stream method
        InputStream is = getResources().openRawResource(R.raw.location_data);
        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        // Initialization
        String line = "";
        // Handling exceptions
        try {
            // If buffer is not empty
            line = br.readLine();
            while (line != null) {  //used to be line = br.readLine() in while
                // use comma as separator columns of CSV
                String[] cols = line.split(",");
                // Print in logcat
                //System.out.println("Column 0 = '" + cols[0] + "', Column 1 = > nxt line
                // '" + cols[1] + "' > nxt line
                // , Column 2: '" + cols[2] + "'");
                allData2.add(cols);
                line= br.readLine();
                Log.wtf("Moose"," entered logout method");

            }
        } catch (IOException e) {
            // Prints throwable details
            e.printStackTrace();

        }
        allData = allData2;
        return allData2;
    }

    /**
     *
     * @param index , the index for item selection
     */
    @Override
    public void onItemSelection(int index) {

        //

        index += 1;

        //
        // The only way to use the clone method
        detailedList = allData.get(index).clone();

        Log.wtf("Moose", "XXX");

        Log.wtf("Moose", Arrays.toString(allData.get(index))+"XXX"+index);

        Intent intent = getIntent();
        startActivity(intent);
        finish();


    }

    /**
     *
     * @param view , has to be fed for the button to work
     */
    public void logout(View view){
        //Intent i = new Intent();
        Log.wtf("Moose"," entered logout method");
        finish();
    }


}


