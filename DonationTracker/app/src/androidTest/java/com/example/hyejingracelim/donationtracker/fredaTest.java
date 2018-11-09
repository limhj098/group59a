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
    public void readDataMethodTest() {

        list = new ArrayList<String>();
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.hyejingracelim.donationtracker", appContext.getPackageName());
    }

}
