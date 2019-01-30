package com.example.hyejingracelim.donationtracker


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 */
/**
 * Required empty public constructor
 */
class ListFrag : ListFragment() {

    internal lateinit var activity: itemSelected // for inspection
    //private itemSelected activity; // for inspection

    /**
     * This interface is for data passage
     */
    interface itemSelected {
        /**
         *
         * @param index for passing the index for list expansion
         */
        fun onItemSelection(index: Int)
    }

    /**
     *
     * @param context , the default way of using the method
     * it is for data passage
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as itemSelected
    }

    /**
     *
     * @param savedInstanceState , the default way of using the method
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //        if(!LocationsActivity.loadData)
        //
        if (LocationsActivity.list == null) {
            list.add("No Data Available !")
        } else {
            list = LocationsActivity.list!!
        }




        Log.d("Moose", "On List <<< ")

        if (getActivity() == null) {
            throw NullPointerException()
        } else
        //setListAdapter(new ArrayAdapter<String>(getActivity(), > nxt line
        // android.R.layout.simple_list_item_1,list)); // for inspection
        {
            listAdapter = ArrayAdapter(getActivity()!!, android.R.layout.simple_list_item_1, list) // for inspection
        }
    }

    /**
     *
     * @param l not used
     * @param v not used
     * @param position for index passage to the other class
     * @param id not used
     *
     * the purpose is to pass the correct index for expansion in detail frag
     */
    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        if (LocationsActivity.loadData)
        //&& position!=0
        {
            activity.onItemSelection(position)
        }
    }

    companion object {
        //static ArrayList<String>  list = new ArrayList<String>(); // for inspection
        internal var list = ArrayList<String>() // for inspection
    }
}// Required empty public constructor
