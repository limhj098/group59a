package com.example.hyejingracelim.donationtracker


import android.content.Intent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Button


/**
 * A simple [Fragment] subclass.
 */
class OptionFrag : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_option, container, false)

        val button = v.findViewById<Button>(R.id.data_entry_button)
        val button1 = v.findViewById<Button>(R.id.viewItems)
        val search = v.findViewById<Button>(R.id.search)
        val map = v.findViewById<Button>(R.id.map)
        button.setOnClickListener {
            val i2 = Intent(activity, DataEntryActivity::class.java)
            startActivity(i2)
            // getActivity().finish();
        }
        button1.setOnClickListener {
            val i3 = Intent(activity, itemDisplayActivity::class.java)
            startActivity(i3)
            // getActivity().finish();
        }

        search.setOnClickListener {
            val i4 = Intent(activity, SearchActivity::class.java)
            startActivity(i4)
            // getActivity().finish();
        }

        map.setOnClickListener {
            val i5 = Intent(activity, MapsActivity::class.java)
            startActivity(i5)
            // getActivity().finish();
        }


        return v
    }

    //    /**
    //     * This method logs the user out of the account using an Intent
    //     * @param view screen for logout button
    //     */
    //    public void logout(View view){
    //        Intent i = new Intent(getActivity(),LoginActivity.class);
    //        startActivity(i);
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    //            Objects.requireNonNull(getActivity()).finish();
    //        }
    //    }

}
