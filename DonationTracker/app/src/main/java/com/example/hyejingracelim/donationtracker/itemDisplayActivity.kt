package com.example.hyejingracelim.donationtracker


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.AdapterView
import android.widget.TextView

import java.io.FileInputStream
import java.io.ObjectInputStream
import java.util.ArrayList
import java.util.HashMap

/**
 * Class Displays Items to Screen
 */
class itemDisplayActivity : AppCompatActivity(), View.OnClickListener {

    private var lv: ListView? = null
    private var tv: TextView? = null


    /**
     * Displays the Item on the screen on the creation of this activity.
     * @param savedInstanceState bundles
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_display_page)
        try {
            tv = findViewById(R.id.itemAttributes)
            lv = findViewById(R.id.itemList)
            lv!!.isClickable = true

            val fis = openFileInput("itemInfo")
            val ois = ObjectInputStream(fis)

            @Suppress("UNCHECKED_CAST")
            val itemInfo = ois.readObject() as HashMap<String, Item>

            ois.close()
            fis.close()
            val itemSet = itemInfo.keys
            val itemList = ArrayList(itemSet)
            val arrayAdapter = ArrayAdapter(
                    //Cant change because ArrayAdapter takes in generics while List does not
                    this,
                    android.R.layout.simple_list_item_1,
                    itemList
            )
            lv!!.adapter = arrayAdapter
            lv!!.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val g = lv!!.getItemAtPosition(position) as String
                val currentItem = itemInfo[g]
                if (currentItem != null) {
                    tv!!.text = currentItem.toString()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val buttonAddMore = findViewById<Button>(R.id.add_item)
        buttonAddMore.setOnClickListener(this)
    }

    /**
     * On click of button, run submit click.
     * @param view view
     */
    override fun onClick(view: View) {
        submitClick()
    }

    /**
     * Clicking submit will start a new intent to DataEntryActivity.
     */
    private fun submitClick() {
        val i = Intent(this, DataEntryActivity::class.java)
        startActivity(i)
        finish()
    }


}
