package com.example.hyejingracelim.donationtracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionFrag extends Fragment{


    public OptionFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_option, container, false);

        final Button button = (Button) v.findViewById(R.id.data_entry_button);
        final Button button1 = (Button) v.findViewById(R.id.viewItems);
        final Button search = (Button) v.findViewById(R.id.search);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i2 = new Intent(getActivity(),DataEntryActivity.class);
                startActivity(i2);
                // getActivity().finish();
            }});
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i3 = new Intent(getActivity(),itemDisplayActivity.class);
                startActivity(i3);
                // getActivity().finish();
            }});

        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i4 = new Intent(getActivity(),SearchActivity.class);
                startActivity(i4);
                // getActivity().finish();
            }});


        return v;
    }

    /**
     * This method logs the user out of the account using an Intent
     *
     */
    public void logout(View view){
        Intent i = new Intent(getActivity(),LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }

}
