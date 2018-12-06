package com.example.hyejingracelim.donationtracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView

import java.io.FileInputStream
import java.io.ObjectInputStream
import java.util.ArrayList
import java.util.HashMap

/**
 * @version 11/8/18
 * @author group59a
 * Class that differentiates the different users that can log in
 * Stores information about different users
 */
class SearchActivity : AppCompatActivity() {

    private val locationButton: Button? = null
    private val categoryButton: Button? = null
    private val itemNameButton: Button? = null
    private var categorySpinner: Spinner? = null
    private var locationSpinner: Spinner? = null
    private var itemNameSearch: EditText? = null

    private val results: TextView? = null

    /**
     *
     * @param savedInstanceState The saved instance state of the Bundle.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_page)

        val locationButton = findViewById<Button>(R.id.button3)
        val categoryButton = findViewById<Button>(R.id.button4)
        val itemNameButton = findViewById<Button>(R.id.button5)

        itemNameSearch = findViewById(R.id.itemName)

        locationSpinner = findViewById(R.id.location)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, User.locations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        locationSpinner!!.adapter = adapter

        categorySpinner = findViewById(R.id.category)

        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, User.categories)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner!!.adapter = adapter2
        locationButton.setOnClickListener {
            try {
                val fis = openFileInput("itemInfo")
                val ois = ObjectInputStream(fis)

                val itemInfo = ois.readObject() as HashMap<String, Item>
                ois.close()
                fis.close()
                val location_data = locationSpinner!!.selectedItem.toString()
                val filteredMap = HashMap<String, Item>()
                for ((key, value) in itemInfo) {
                    if (value.location == location_data) {
                        filteredMap[key] = value
                    }
                }
                val tv1 = findViewById<TextView>(R.id.textView4)
                val lv1 = findViewById<ListView>(R.id.searchResult)
                lv1.isClickable = true
                ois.close()
                fis.close()
                val itemSet = filteredMap.keys
                val itemList = ArrayList(itemSet)
                val arrayAdapter1 = ArrayAdapter(
                        baseContext,
                        android.R.layout.simple_list_item_1,
                        itemList
                )
                lv1.adapter = arrayAdapter1
                lv1.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    val g = lv1.getItemAtPosition(position) as String
                    val currentItem = filteredMap[g]!!
                    tv1.text = currentItem.toString()
                }
            } catch (ignored: Exception) {
            }

            //  assertEquals(expectedMap, filteredMap);
        }

        categoryButton.setOnClickListener(/**/)
        /**
         *
         * @param v View v that is used when the button is clicked.
         */
        {
            try {
                val fis = openFileInput("itemInfo")
                val ois = ObjectInputStream(fis)

                val itemInfo = ois.readObject() as HashMap<String, Item>
                ois.close()
                fis.close()
                val category_data = categorySpinner!!.selectedItem.toString()
                val filteredMap2 = HashMap<String, Item>()
                for ((key, value) in itemInfo) {
                    if (value.category == category_data) {
                        filteredMap2[key] = value
                    }
                }
                val tv1 = findViewById<TextView>(R.id.textView4)
                val lv1 = findViewById<ListView>(R.id.searchResult)
                lv1.isClickable = true
                ois.close()
                fis.close()
                val itemSet = filteredMap2.keys
                val itemList = ArrayList(itemSet)
                val arrayAdapter1 = ArrayAdapter(
                        baseContext,
                        android.R.layout.simple_list_item_1,
                        itemList
                )
                lv1.adapter = arrayAdapter1
                lv1.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    /**
                     *
                     * @param parent AdapterView parent
                     * @param view View class used.
                     * @param position An int of the position.
                     * @param id A long of the position.
                     */
                    /**
                     *
                     * @param parent AdapterView parent
                     * @param view View class used.
                     * @param position An int of the position.
                     * @param id A long of the position.
                     */
                    val g = lv1.getItemAtPosition(position) as String
                    val currentItem = filteredMap2[g]!!
                    tv1.text = currentItem.toString()
                }
            } catch (ignored: Exception) {
            }

            //  assertEquals(expectedMap, filteredMap);
        }
        itemNameButton.setOnClickListener(/**/)
        /**
         *
         * @param v View that is used when button is clicked.
         */
        {
            try {
                val fis = openFileInput("itemInfo")
                val ois = ObjectInputStream(fis)

                val itemInfo = ois.readObject() as HashMap<String, Item>
                ois.close()
                fis.close()
                val name_data = itemNameSearch!!.toString()
                val filteredMap3 = HashMap<String, Item>()
                for ((key, value) in itemInfo) {
                    if (key.equals(name_data, ignoreCase = true)) {
                        filteredMap3[key] = value
                    }
                }
                val tv1 = findViewById<TextView>(R.id.textView4)
                val lv1 = findViewById<ListView>(R.id.searchResult)
                lv1.isClickable = true
                ois.close()
                fis.close()
                val itemSet = filteredMap3.keys
                val itemList = ArrayList(itemSet)
                val arrayAdapter1 = ArrayAdapter(
                        baseContext,
                        android.R.layout.simple_list_item_1,
                        itemList
                )
                lv1.adapter = arrayAdapter1
                lv1.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    /**
                     *
                     * @param parent AdapterView parent
                     * @param view View class used.
                     * @param position An int of the position.
                     * @param id A long of the position.
                     */
                    /**
                     *
                     * @param parent AdapterView parent
                     * @param view View class used.
                     * @param position An int of the position.
                     * @param id A long of the position.
                     */
                    val g = lv1.getItemAtPosition(position) as String
                    val currentItem = filteredMap3[g]!!
                    tv1.text = currentItem.toString()
                }
            } catch (ignored: Exception) {
            }

            //  assertEquals(expectedMap, filteredMap);
        }
    }
}
