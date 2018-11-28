package com.example.hyejingracelim.donationtracker;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class fredaTest {

    @Test
    public void itemToString_returnEmpty() {
        Item someItem = new Item();

        assertEquals(someItem.toString(), "");
    }

    @Test
    public void testToString() {
        Item a = new Item();
        a.setDonationTime("5:00pm");
        a.setLocation("location 1");
        a.setCategory("Clothes");
        a.setLongDescription("A Yellow sundress with polka dots");
        a.setValue("$5");

        assertEquals(a.toString(), "Time : " + a.getDonationTime() + " Location : " + a.getLocation()
                        + " Category : " + a.getCategory() + " Description : " + a.getLongDescription()
                        + " Value : " + a.getValue());
    }

}
