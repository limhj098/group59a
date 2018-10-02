package com.example.hyejingracelim.donationtracker;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    public static List<String> legalUsers = Arrays.asList("Guest", "Customer", "Admin", "Donator", "Branch Manger",
            "Warehouse Employee", "Donation Recorder Employee");

    private String _name;
    private String _email;
    private String _password;
    private UserType _userType;

    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    public String getEmail() { return _email; }
    public void setEmail(String email) { _email = email; }

    public String getPassword() { return _password; }
    public void setPassword(String password) { _password = password; }

    public UserType getUserType() {return _userType;}
    public void setUserType(UserType userType) {_userType = userType;}


    public User(String name, String email, String password, UserType userType) {
        _name = name;
        _email = email;
        _password = password;
        _userType = userType;
    }

    public User(String name, String email, String password) {this(name, email, password, UserType.GUEST);}

    public User() {
        this("enter name", "enter email", "enter password", UserType.GUEST);

    }

}
