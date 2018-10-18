package com.example.hyejingracelim.donationtracker;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFrag extends ListFragment {


    public DetailFrag() {
        // Required empty public constructor
    }

    static ArrayList<String>  list = new ArrayList<String>();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        String[] arr = new String[12];

        if (LocationsActivity.detailedList == null)
            arr[0] = ("No Data Availble !");
        else {
            arr = LocationsActivity.detailedList;

            for (int i = 0; i < arr.length; i++) {
                list.add(arr[i]);
            }


            setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list));
        }


    }
}



