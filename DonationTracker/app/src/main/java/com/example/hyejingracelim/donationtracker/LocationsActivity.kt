package com.example.hyejingracelim.donationtracker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.util.ArrayList
import java.util.Arrays

import com.facebook.AccessToken
import com.facebook.login.LoginManager

/**
 *
 */
class LocationsActivity : AppCompatActivity(), ListFrag.itemSelected {

    /**
     *
     * @param savedInstanceState , the way this method works
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations)
    }

    private fun goLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    /*
    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
*/
    /**
     *
     * @param view , has to be fed in here for the button to work
     */
    fun readData(view: View) {
        loadData = true
        //list =new ArrayList<String>(); // for inspection
        list = ArrayList() // for inspection

        val allData = readCSV(resources.openRawResource(R.raw.location_data))
        Log.wtf("Moose", " entered logout method")
        Log.d("Moose", Arrays.toString(allData[0]))
        val ToastMsg = "Data have been loaded successfully"
        // The only way to use the show method
        Toast.makeText(applicationContext, ToastMsg, Toast.LENGTH_LONG).show()


        list!!.clear()

        for (str in allData) {
            list!!.add(str[1] + " \n(" + str[8] + ")")

        }
        list!!.removeAt(0)

        val intent = intent
        finish()
        startActivity(intent)

    }

    /**
     * @param input t=input
     * @return , returns a String arrayList
     * method changed to private for inspection
     * then public for testing
     */
    fun readCSV(input: InputStream): ArrayList<Array<String>> {

        //allData = new ArrayList<String[]>(); // for inspection
        //allData = new ArrayList<>(); // for inspection
        val allData2 = ArrayList<Array<String>>() // for inspection


        // Read the raw csv file
        // The only way to use the input stream method

        // Reads text from character-input stream, buffering characters for efficient reading
        val br: BufferedReader

        br = BufferedReader(InputStreamReader(input, Charset.forName("UTF-8")))

        // Initialization
        var line: String? = ""
        // Handling exceptions
        try {
            // If buffer is not empty
            line = br.readLine()
            while (line != null) {  //used to be line = br.readLine() in while
                // use comma as separator columns of CSV

                val cols = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                // Print in logcat
                //System.out.println("Column 0 = '" + cols[0] + "', Column 1 = > nxt line
                // '" + cols[1] + "' > nxt line
                // , Column 2: '" + cols[2] + "'");
                allData2.add(cols)
                line = br.readLine()
                Log.wtf("Moose", " entered logout method")

            }
        } catch (e: IOException) {
            // Prints throwable details
            e.printStackTrace()

        }

        allData = allData2
        return allData2
    }

    /**
     *
     * @param index , the index for item selection
     */
    override fun onItemSelection(index: Int) {
        var index = index

        //

        index += 1

        //
        // The only way to use the clone method
        detailedList = allData[index].clone()

        Log.wtf("Moose", "XXX")

        Log.wtf("Moose", Arrays.toString(allData[index]) + "XXX" + index)

        val intent = intent
        startActivity(intent)
        finish()


    }

    /**
     *
     * @param view , has to be fed for the button to work
     */
    fun logout(view: View) {
        //Intent i = new Intent();
        Log.wtf("Moose", " entered logout method")
        finish()
    }

    companion object {
        internal var loadData = false
        internal var allData = ArrayList<Array<String>>() // for inspection
        //static ArrayList<String[]>  allData ; // for inspection
        //static ArrayList<String>  list = null; // for inspection
        internal var list: ArrayList<String>? = null // for inspection
        internal var detailedList: Array<String>? = null

    }


}


