package com.example.hyejingracelim.donationtracker;


import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * @version 11/8/18
 * @author group59a
 * Class that differentiates the different users that can log in
 * Stores information about different users
 */
public class User {
    public static List<String> legalUsers
            = Arrays.asList("Guest", "Customer", "Admin", "Donator", "Branch Manager",
            "Warehouse Employee", "Donation Recorder Employee");

    public static List<String> categories
            = Arrays.asList("Clothing", "Hat", "Electronics", "Kitchen", "Household",
            "Other");

    public static List<String> locations
            = Arrays.asList("Location 1", "Location 2", "Location 3", "Location 4", "Location 5",
            "Location 6", "ALL");

    private String _name;
    private String _email;
    private String _password;
    private UserType _userType;

    @SuppressWarnings("FieldCanBeLocal")
    private String name;
    private String time;
    private String shortDescript;
    private String fullDescript;
    private String location_data;
    private String category_data;
    private String price;

    /**
     * user
     */
    public User(){

    }

    /**
     *
     * @param name     The name of a location.
     * @param time     The time in which the location is registered.
     * @param shortDescript   The short description of a location.
     * @param fullDescript    The full description of a location.
     * @param loc      The location data of a location.
     * @param category The category of a location.
     * @param price    The price of a location.
     */
    public User(String name, String time, String
            shortDescript, String fullDescript, String loc, String category, String price){
        this.name = name;
        this.time = time;
        this.shortDescript = shortDescript;
        this.fullDescript = fullDescript;
        this.location_data = loc;
        this.category_data = category;
        this.price = price;
    }
    /**
     * @return name
     */
    public String getName(){
        String name1;
        //noinspection ObjectEqualsNull,ConstantConditions
        if (_name.equals("") || _name.equals(null)) {
            return "";
        } else {
            name1 = _name;
        }
        return name1;
    }
    /**
     * @return time
     */
    public String getTime() {return time;}

    /**
     * @return shortDescript
     */
    public String getShortDescription(){return shortDescript;}

    /**
     * @return fullDescript
     */
    public String getFullDescription(){return fullDescript;}

    /**
     * @return location_data
     */
    public String getLocation(){return location_data;}

    /**
     *
     * @param location The email of a user.
     */
    public void setLocation(String location) { location_data = location; }

    /**
     * @return category_data
     */
    public String getCategory(){return category_data;}

    /**
     * @return price
     */
    public String getPrice(){return price; }

    /**
     *
     * @return _name
     */
    public String getUserName() { return _name; }
  //  public void setName(String name) { _name = name; }

    /**
     *
     * @return _email
     */
    public String getEmail() { return _email; }

    /**
     *
     * @param email The email of a user.
     */
    public void setEmail(String email) { _email = email; }

    /**
     *
     * @return _password
     */
    public String getPassword() { return _password; }

    /**
     *
     * @param password The password of a user.
     */
    public void setPassword(String password) { _password = password; }

    /**
     *
     * @return _userType
     */
    public UserType getUserType() {return _userType;}

    /**
     *
     * @param userType The userType of a user.
     */
    public void setUserType(UserType userType) {_userType = userType;}

    /**
     *  @param name The name of a user.
     * @param email The email of a user.
     * @param password The password of a user.
     */
    public User(String name, String email, String password) {
        _name = name;
        _email = email;
        _password = password;
        _userType = UserType.GUEST;
    }

    /**
     * Converts user to string
     * @return user as string
     */
    @NonNull
    @Override
    public String toString() {
        if (_name != null) {
            String returnUser ="";
            returnUser = returnUser + "Name: ";
            returnUser = returnUser + _name;
            if (location_data!=null) {
                returnUser = returnUser + " Location: ";
                returnUser = returnUser + location_data;
            }
            if (_userType != null) {
                returnUser = returnUser + " User Type: ";
                returnUser = returnUser + _userType;
            }
            return returnUser;
        }
        else {
            return "User not Found";
        }
    }

}
