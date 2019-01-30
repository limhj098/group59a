package com.example.hyejingracelim.donationtracker


import java.util.Arrays

/**
 * @version 11/8/18
 * @author group59a
 * Class that differentiates the different users that can log in
 * Stores information about different users
 */
class User {

    /**
     *
     * @return _name
     */
    var userName: String? = null
    //  public void setName(String name) { _name = name; }

    /**
     *
     * @return _email
     */
    /**
     *
     * @param email The email of a user.
     */
    var email: String? = null
    /**
     *
     * @return _password
     */
    /**
     *
     * @param password The password of a user.
     */
    var password: String? = null
    /**
     *
     * @return _userType
     */
    /**
     *
     * @param userType The userType of a user.
     */
    var userType: UserType? = null

    private var name: String = ""
    /**
     * @return time
     */
    var time: String = ""
    /**
     * @return shortDescript
     */
    var shortDescription: String = ""
    /**
     * @return fullDescript
     */
    var fullDescription: String = ""
    /**
     * @return location_data
     */
    /**
     *
     * @param location The email of a user.
     */
    var location: String? = null
    /**
     * @return category_data
     */
    var category: String = ""
    /**
     * @return price
     */
    var price: String = ""

    /**
     * user
     */
    constructor() {

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
    constructor(name: String, time: String, shortDescript: String, fullDescript: String, loc: String, category: String, price: String) {
        this.name = name
        this.time = time
        this.shortDescription = shortDescript
        this.fullDescription = fullDescript
        this.location = loc
        this.category = category
        this.price = price
    }

    /**
     * @return name
     */
    fun getName(): String {
        val name1: String
        //noinspection ObjectEqualsNull,ConstantConditions
        if (userName == "" || userName == null) {
            return ""
        } else {
            name1 = userName as String
        }
        return name1
    }

    /**
     * @param name The name of a user.
     * @param email The email of a user.
     * @param password The password of a user.
     */
    constructor(name: String, email: String, password: String) {
        userName = name
        this.email = email
        this.password = password
        userType = UserType.GUEST
    }

    /**
     * Converts user to string
     * @return user as string
     */
    override fun toString(): String {
        if (userName != null) {
            var returnUser = ""
            returnUser = returnUser + "Name: "
            returnUser = returnUser + userName
            if (location != null) {
                returnUser = "$returnUser Location: "
                returnUser = returnUser + location!!
            }
            if (userType != null) {
                returnUser = "$returnUser User Type: "
                returnUser = returnUser + userType!!
            }
            return returnUser
        } else {
            return "User not Found"
        }
    }

    companion object {
        var legalUsers = Arrays.asList("Guest", "Customer", "Admin", "Donator", "Branch Manager",
                "Warehouse Employee", "Donation Recorder Employee")

        var categories = Arrays.asList("Clothing", "Hat", "Electronics", "Kitchen", "Household",
                "Other")

        var locations = Arrays.asList("Location 1", "Location 2", "Location 3", "Location 4", "Location 5",
                "Location 6", "ALL")
    }

}
