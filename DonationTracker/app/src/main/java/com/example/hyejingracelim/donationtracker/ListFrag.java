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


    public ListFrag() {
        // Required empty public constructor
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_list, container, false);
//    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<String> list = new ArrayList<String>();
        list.add("comp 1");
        list.add("comp 2");



       Log.d("Moose","***  ***   ***  ***  *** ***");


        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,list));
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    }
}
