package com.example.hyejingracelim.donationtracker;


import android.content.Context;
import android.content.Intent;
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
import java.util.Arrays;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends ListFragment {

    itemSelected activity;

    public interface itemSelected{
        void onItemSelection(int index);
    }
    static ArrayList<String>  list = new ArrayList<String>();

    public ListFrag() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (itemSelected) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        if(!LocationsActivity.loadData)
//
        if(LocationsActivity.list == null)
        list.add("No Data Availble !");
        else
            list = LocationsActivity.list;




      Log.d("Moose","On List <<< ");


        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,list));
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        activity.onItemSelection(position);
    }
}
