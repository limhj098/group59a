package com.example.hyejingracelim.donationtracker;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.hyejingracelim.donationtracker.ListFrag.list;

public class LocationsActivity extends AppCompatActivity implements ListFrag.itemSelected {

    static boolean loadData = false;
    static ArrayList<String[]>  allData = new ArrayList<String[]>();
    static ArrayList<String>  list = null;
    static String []  detailedList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
    }
//

    public void readData(View view) {
        loadData =true;
        list =new ArrayList<String>();

        ArrayList<String[]>  allData= readCSV();
        Log.d("Moose",Arrays.toString(allData.get(0)));
        Toast.makeText(getApplicationContext(),"Data have been loaded successfully",Toast.LENGTH_LONG).show();

        list.clear();

        for (String[] str: allData){
            list.add(str[1]+" \n("+str[8]+")");

        }
        list.remove(0);

        Intent intent = getIntent();
        finish();
        startActivity(intent);


//
//        ListFrag l = new ListFrag();
//        l.onActivityCreated(null);
    }

    public ArrayList<String[]> readCSV(){

          allData = new ArrayList<String[]>();


        // Read the raw csv file
        InputStream is = getResources().openRawResource(R.raw.location_data);
        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        // Initialization
        String line = "";
        // Handling exceptions
        try {
            // If buffer is not empty

            while ((line = br.readLine()) != null) {
                // use comma as separator columns of CSV
                String[] cols = line.split(",");
                // Print in logcat
                //System.out.println("Coulmn 0 = '" + cols[0] + "', Column 1 = '" + cols[1] + "', Column 2: '" + cols[2] + "'");
                allData.add(cols);
            }
        } catch (IOException e) {
            // Prints throwable details
            e.printStackTrace();
        }
        return allData;
    }

    @Override
    public void onItemSelection(int index) {


            // ListView lv = findViewById(R.id.listDet);
            //Log.d("Moose",Arrays.toString(detailedList)+" >>>>>>");

//            detailedList = new String[allData.get(index).length];
            index +=1;
            detailedList = allData.get(index).clone();


            Log.d("Moose", Arrays.toString(allData.get(index))+" The data for the location:"+index);

//
            Intent intent = getIntent();
            finish();
            startActivity(intent);


    }

    public void logout(View view){
        //Intent i = new Intent();
        finish();
    }


}


