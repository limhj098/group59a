package com.example.hyejingracelim.donationtracker;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFrag extends ListFragment {

    /**
     * Required empty public constructor
     */
    public DetailFrag() {
        // Required empty public constructor
    }

    static ArrayList<String>  list = new ArrayList<String>(); // for inspection
    //static ArrayList<String>  list = new ArrayList<>();

    /**
     *
     * @param context , copied form online site , can be deleted
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     *
     * @param savedInstanceState , default usage of the method
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        String[] arr;

        if ( (LocationsActivity.detailedList == null) || (list.isEmpty())) {
            list.clear();
            list.add("Choose a location to Display");
        }

        else {
            list.clear();
            arr = LocationsActivity.detailedList.clone();

            for (int i = 0; i < arr.length; i++) {
                list.add(LocationsActivity.allData.get(0)[i]+ " : " + arr[i]);
            }

        }
            if (getActivity() == null) {
                throw new NullPointerException();
            } else
            //setListAdapter(new ArrayAdapter<String>(getActivity() > nxt line
                // , android.R.layout.simple_list_item_1, list)); // for inspection

            {
                // the easiest wat to set up the list adapter
                setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list)); // for inspection
            }



    }
}



