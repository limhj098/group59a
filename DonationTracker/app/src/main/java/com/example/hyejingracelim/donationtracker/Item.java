package com.example.hyejingracelim.donationtracker;

import java.io.Serializable;

public class Item implements Serializable {
    public String donationTime;
    public String location;
    public String value;
    public String shortDescription;
    public String longDescription;
    public String catagory;

    @Override
    public String toString() {
        if (donationTime != null){
            String ans = "";
            ans = ans.concat("Time : ");
            ans = ans.concat(donationTime);
            if (location != null){
                ans = ans.concat(" Location : ");
                ans = ans.concat(location);
            }
            if (catagory != null){
                ans = ans.concat(" Catagory : ");
                ans = ans.concat(catagory);
            }
            if (longDescription != null){
                ans = ans.concat(" Description : ");
                ans = ans.concat(longDescription);
            }
            if (value != null){
                ans = ans.concat(" Value : ");
                ans = ans.concat(value);
            }
            return ans;
        }
        else return "Time not Recorded";
    }
    public String getDonationTime(){return donationTime;}
    public String getLocation() {return location;}
    public String getValue(){return value;}
    public String getshortDescription(){return shortDescription;}
    public String getLongDescription(){return longDescription;}
    public String getCatagory(){return catagory;}

}
