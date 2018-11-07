package com.example.hyejingracelim.donationtracker;

import java.io.Serializable;

/**
 * Stores item information
 */
public class Item implements Serializable {

    public String donationTime;
    public String location;
    public String value;
    public String shortDescription;
    public String longDescription;
    public String category;

    /**
     * Converts item to string
     * @return item as string
     */
    @Override
    public String toString() {
        if (donationTime != null){
            String ans = "";
            ans = ans + "Time : ";
            ans = ans + donationTime;
            if (location != null){
                ans = ans + " Location : ";
                ans = ans + location;
            }
            if (category != null){
                ans = ans + " Category : ";
                ans = ans + category;
            }
            if (longDescription != null){
                ans = ans + " Description : ";
                ans = ans + longDescription;
            }
            if (value != null){
                ans = ans + " Value : ";
                ans = ans + value;
            }
            return ans;
        }
        else {
            return "Time not Recorded";
        }
    }
}
