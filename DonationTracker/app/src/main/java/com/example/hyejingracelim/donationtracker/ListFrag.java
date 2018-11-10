package com.example.hyejingracelim.donationtracker;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFrag extends ListFragment {

    //itemSelected activity; // for inspection
    private itemSelected activity; // for inspection

    /**
     *  This interface is for data passage
     */
    public interface itemSelected{
        /**
         *
         * @param index for passing the index for list expansion
         */
        void onItemSelection(int index);
    }
    //static ArrayList<String>  list = new ArrayList<String>(); // for inspection
    static ArrayList<String>  list = new ArrayList<>(); // for inspection


    /**
     *  Required empty public constructor
     */
    public ListFrag() {
        // Required empty public constructor
    }

    /**
     *
     * @param context , the default way of using the method
     * it is for data passage
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (itemSelected) context;
    }

    /**
     *
     * @param savedInstanceState , the default way of using the method
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        if(!LocationsActivity.loadData)
//
        if(LocationsActivity.list == null) {
            list.add("No Data Available !");
        } else {
            list = LocationsActivity.list;
        }




      Log.d("Moose","On List <<< ");

      if (getActivity() == null) {
          throw new NullPointerException();
      } else
        //setListAdapter(new ArrayAdapter<String>(getActivity(), > nxt line
          // android.R.layout.simple_list_item_1,list)); // for inspection

      {
          setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,list)); // for inspection
      }
    }

    /**
     *
     * @param l not used
     * @param v not used
     * @param position for index passage to the other class
     * @param id not used
     *
     *  the purpose is to pass the correct index for expansion in detail frag
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if(LocationsActivity.loadData ) //&& position!=0
        {
            activity.onItemSelection(position);
        }
    }
}
