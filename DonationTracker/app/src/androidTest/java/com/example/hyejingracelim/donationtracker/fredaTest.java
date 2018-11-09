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
        a.donationTime = "5:00pm";
        a.location = "location 1";
        a.category = "Clothes";
        a.longDescription = "A Yellow sundress with polka dots";
        a.value = "$5";

        assertEquals(a.toString(), "Time : " + a.donationTime + " Location : " + a.location
                        + " Category : " + a.category + " Description : " + a.longDescription
                        + " Value : " + a.value);
    }

}
