package com.example.hyejingracelim.donationtracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner


import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.ObjectOutput

import java.util.HashMap

/**
 * This class Contains a Data entry method for information into the application
 */
class DataEntryActivity : AppCompatActivity(), View.OnClickListener {

    private var timeField: EditText? = null
    private var nameField: EditText? = null
    private var shortDescriptionField: EditText? = null
    private var fullDescriptionField: EditText? = null
    private var categorySpinner: Spinner? = null
    private var locationSpinner: Spinner? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_entry)

        timeField = findViewById(R.id.time)
        nameField = findViewById(R.id.name)
        shortDescriptionField = findViewById(R.id.short_description)
        fullDescriptionField = findViewById(R.id.full_description)

        locationSpinner = findViewById(R.id.location_spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, User.locations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        locationSpinner!!.adapter = adapter

        categorySpinner = findViewById(R.id.category_spinner)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, User.categories)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner!!.adapter = adapter2


        val buttonSubmit = findViewById<Button>(R.id.submit)
        buttonSubmit.setOnClickListener(this)

        //     Button buttonCancel = (Button) findViewById(R.id.cancel);
        //   buttonCancel.setOnClickListener();
    }

    private fun submitClick() {
        val time: String
        val name: String
        val shortDescription: String
        val fullDescription: String
        val location_data: String
        val category_data: String

        if (timeField!!.text != null) {
            time = timeField!!.text.toString()
        } else {
            time = "not set"
        }
        if (nameField!!.text != null) {
            name = nameField!!.text.toString()
        } else {
            name = "not set"
        }
        if (shortDescriptionField!!.text != null) {
            shortDescription = shortDescriptionField!!.text.toString()
        } else {
            shortDescription = "not set"
        }
        if (fullDescriptionField!!.text != null) {
            fullDescription = fullDescriptionField!!.text.toString()
        } else {
            fullDescription = "not set"
        }
        location_data = locationSpinner!!.selectedItem.toString()
        category_data = categorySpinner!!.selectedItem.toString()

        val a = Item()
        a.donationTime = time
        a.location = location_data
        a.category = category_data
        a.longDescription = fullDescription
        a.shortDescription = shortDescription
        try {
            // Creates a file in the primary external storage space of the
            // If the file does not exists, it is created.
            val testFile = File(this.getExternalFilesDir(null), "itemInfo")
            if (!testFile.exists()) {
                testFile.createNewFile() //need this to create a running list
                val ofi = openFileOutput("itemInfo", 0)
                val oos = ObjectOutputStream(ofi)
                val test = HashMap<String, Item>()
                test[name] = a
                oos.writeObject(test)
                oos.close()
                ofi.close()
            } else {
                val fis = openFileInput("itemInfo")
                val ois = ObjectInputStream(fis)
                val itemInfo = ois.readObject() as HashMap<String, Item>
                ois.close()
                fis.close()

                val ofi = openFileOutput("itemInfo", 0)
                val oos = ObjectOutputStream(ofi)
                //Map<String, Item> test = itemInfo;
                itemInfo[name] = a
                oos.writeObject(itemInfo)
                oos.close()
                ofi.close()
            }//adds to file

        } catch (e: Exception) {
            e.printStackTrace()
        }

        val i = Intent(this, itemDisplayActivity::class.java)
        startActivity(i)
        finish()

    }

    override fun onClick(view: View) {
        submitClick()
    }

    /**
     * This class creates an intent that leads back to locations activity when clicked
     * @param view parameter that takes in view
     */
    fun cancelClick(view: View) {
        val i2 = Intent(this, LocationsActivity::class.java)
        startActivity(i2)
        finish()
    }


}
