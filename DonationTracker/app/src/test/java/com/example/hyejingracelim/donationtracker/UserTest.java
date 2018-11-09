package com.example.hyejingracelim.donationtracker;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * @version 11/8/18
 * @author group59a
 * Class that differentiates the different users that can log in
 * Stores information about different users
 */
public class UserTest {

    @Test
    public void getNameTest() {
        User user = new User("JinwooPlace", "1:00", "Place",
                   "Jinwoo's place", "Atlanta", "small", "10000");
        assertEquals("Test failed. Wrong name outputted.","JinwooPlace",
                user.getName());
    }
}