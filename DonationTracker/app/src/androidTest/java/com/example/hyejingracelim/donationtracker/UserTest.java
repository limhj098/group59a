package com.example.hyejingracelim.donationtracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getName() {
        User user = new User("", "mail@a.com", "123123");
        assertEquals("", user.getName());
        User user1 = new User("jinwoo", "", "");
        assertEquals("jinwoo", user1.getName());
    }
}