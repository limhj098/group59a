package com.example.hyejingracelim.donationtracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionFrag extends Fragment {


    public OptionFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option, container, false);
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
