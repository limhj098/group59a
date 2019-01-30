package com.example.hyejingracelim.donationtracker;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class graceTest {

    @Test public void attemptLoginNull() {
        User someUser = new User();

        assertEquals(someUser.toString(), "User not Found");
    }
    @Test
    public void testToString() {
        User s = new User("Grace", "limhj098@gmail.com", "Trythis123");
        s.setLocation("Georgia Tech");


        assertEquals(s.toString(), "Name: " + s.getName() + " Location: " + s.getLocation()
                        + " User Type: " + s.getUserType());
    }
}



