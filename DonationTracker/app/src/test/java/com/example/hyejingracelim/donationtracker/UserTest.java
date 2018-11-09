package com.example.hyejingracelim.donationtracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getNameTest() {
        User user = new User("JinwooPlace", "1:00", "Place",
                   "Jinwoo's place", "Atlanta", "small", "10000");
        assertEquals("Test failed. Wrong name outputted.","JinwooPlace",
                user.getName());
    }
}