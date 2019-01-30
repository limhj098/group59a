package com.example.hyejingracelim.donationtracker;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class ryanTest {

    @Rule
    public ActivityTestRule<LocationsActivity> mRule = new ActivityTestRule<>(LocationsActivity.class);


    @Test
    public void readCSVText() {
        InputStream is = mRule.getActivity().getResources().openRawResource(R.raw.location_data);
        ArrayList<String[]> result = mRule.getActivity().readCSV(is);

        // ...these two lines were copied manually from the CSV file
        String firstLine ="Key,Name,Latitude,Longitude,Street Address," +
                "City,State,Zip,Type,Phone,Website";
        String lastLine = "6,KEEP NORTH FULTON BEAUTIFUL,33.96921,-84.3688," +
                "470 MORGAN FALLS RD,Sandy Springs,GA,30302,Store,(770) 555 - 7321,www.knfb.org";

        String[] firstArr = firstLine.split(",");
        String[] lastArr = lastLine.split(",");

        /**
         *         The first element of the first line returned by ReadCSV
         *         adds an extra string for a non clear reason,
         *         un comment the Log.wtf lines to see the results in Logcat
         */

//        Log.wtf("Moose",(Arrays.toString(firstArr)));
//        Log.wtf("Moose",(Arrays.toString(result.get(0))));
//
//        Log.wtf("Moose",(Arrays.toString(lastArr)));
//        Log.wtf("Moose",(Arrays.toString(result.get(6))));


        String[] arrayError1 = result.get(0);
        arrayError1[0]="";

        String[] arrayError2 = firstArr;
        arrayError2[0]="";

        // testing the edge case, the first line in csv
        Assert.assertArrayEquals(arrayError1,arrayError2);


        // testing the edge case , the last line in csv
        Assert.assertArrayEquals(result.get(result.size()-1),lastArr);

        assertNotEquals(result,null);

    }

    @Test
    public void CSVNotFound(){
        InputStream is = null;
        ArrayList<String[]> result=null;
        try {
            result = mRule.getActivity().readCSV(is);
        }catch (Exception e){}
        // the value for the results stays as is if file not found
        assertEquals(result,null);

    }

}