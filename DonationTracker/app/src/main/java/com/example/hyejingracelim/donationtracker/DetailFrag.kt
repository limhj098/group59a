package com.example.hyejingracelim.donationtracker


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.widget.ArrayAdapter

import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 */
/**
 * Required empty public constructor
 */
class DetailFrag : ListFragment() {
    //static ArrayList<String>  list = new ArrayList<>();

    /**
     *
     * @param context , copied form online site , can be deleted
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    /**
     *
     * @param savedInstanceState , default usage of the method
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val arr: Array<String>

        if (LocationsActivity.detailedList == null || list.isEmpty()) {
            list.clear()
            list.add("Choose a location to Display")
        } else {
            list.clear()
            arr = LocationsActivity.detailedList!!.clone()

            for (i in arr.indices) {
                list.add(LocationsActivity.allData[0][i] + " : " + arr[i])
            }

        }
        if (activity == null) {
            throw NullPointerException()
        } else
        //setListAdapter(new ArrayAdapter<String>(getActivity() > nxt line
        // , android.R.layout.simple_list_item_1, list)); // for inspection
        {
            // the easiest wat to set up the list adapter
            listAdapter = ArrayAdapter(activity!!, android.R.layout.simple_list_item_1, list) // for inspection
        }


    }

    companion object {

        internal var list = ArrayList<String>() // for inspection
    }
}// Required empty public constructor



