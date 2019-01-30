package com.example.hyejingracelim.donationtracker

import android.support.v4.app.FragmentActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * @version 11/8/18
 * @author group59a
 * Class that differentiates the different users that can log in
 * Stores information about different users
 */
class MapsActivity : FragmentActivity(), OnMapReadyCallback {
    /**
     *
     * @param savedInstanceState The saved instance state of the Bundle.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = (supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?)!!
        mapFragment.getMapAsync(this)
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
    override fun onMapReady(googleMap: GoogleMap) {

        // Add a marker in Sydney and move the camera
        val AFD = LatLng(33.75416, -84.37742)
        val BNG = LatLng(33.73182, -84.43971)
        val MIN = LatLng(33.70866, -84.41853)
        val PAV = LatLng(33.80129, -84.25537)
        val CON = LatLng(33.71747, -84.2521)
        val BEA = LatLng(33.96921, -84.3688)

        googleMap.addMarker(MarkerOptions().position(AFD).title("AFD Station")
                .snippet("Phone Number: (404) 555 - 3456"))
        googleMap.addMarker(MarkerOptions().position(BNG).title("BOYS & GIRLS CLUB W.W. WOODFOLK")
                .snippet("Phone Number: (404) 555 - 1234"))
        googleMap.addMarker(MarkerOptions().position(MIN).title("PATHWAY UPPER ROOM CHRISTIAN MINISTRIES")
                .snippet("Phone Number: (404) 555 - 5432"))
        googleMap.addMarker(MarkerOptions().position(PAV).title("PAVILION OF HOPE INC")
                .snippet("Phone Number: (404) 555 - 8765"))
        googleMap.addMarker(MarkerOptions().position(CON).title("D&D CONVENIENCE STORE")
                .snippet("Phone Number:(404) 555 - 9876"))
        googleMap.addMarker(MarkerOptions().position(BEA).title("KEEP NORTH FULTON BEAUTIFUL")
                .snippet("Phone Number: (770) 555 - 7321"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(AFD))
    }
}
