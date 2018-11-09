package com.example.hyejingracelim.donationtracker;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * @version 11/8/18
 * @author group59a
 * Class that differentiates the different users that can log in
 * Stores information about different users
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    /**
     *
     * @param savedInstanceState The saved instance state of the Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Add a marker in Sydney and move the camera
        LatLng AFD = new LatLng(33.75416,-84.37742);
        LatLng BNG = new LatLng(33.73182,-84.43971);
        LatLng MIN = new LatLng(33.70866,-84.41853);
        LatLng PAV = new LatLng(33.80129,-84.25537);
        LatLng CON = new LatLng(33.71747,-84.2521);
        LatLng BEA = new LatLng(33.96921,-84.3688);

        googleMap.addMarker(new MarkerOptions().position(AFD).title("AFD Station").snippet("Phone Number: (404) 555 - 3456"));
        googleMap.addMarker(new MarkerOptions().position(BNG).title("BOYS & GIRLS CLUB W.W. WOOLFOLK").snippet("Phone Number: (404) 555 - 1234"));
        googleMap.addMarker(new MarkerOptions().position(MIN).title("PATHWAY UPPER ROOM CHRISTIAN MINISTRIES").snippet("Phone Number: (404) 555 - 5432"));
        googleMap.addMarker(new MarkerOptions().position(PAV).title("PAVILION OF HOPE INC").snippet("Phone Number: (404) 555 - 8765"));
        googleMap.addMarker(new MarkerOptions().position(CON).title("D&D CONVENIENCE STORE").snippet("Phone Number:(404) 555 - 9876"));
        googleMap.addMarker(new MarkerOptions().position(BEA).title("KEEP NORTH FULTON BEAUTIFUL").snippet("Phone Number: (770) 555 - 7321"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(AFD));
    }
}
