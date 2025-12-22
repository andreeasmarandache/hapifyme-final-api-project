package com.hapifyme.api.utils;

public class DataGenerator {

    public static String randomEmail() {
        return "test_user_" + System.currentTimeMillis() + "@gmail.com";
    }

    public static String randomFirstName() {
        return "TestFirst" + System.currentTimeMillis();
    }

    public static String randomLastName() {
        return "TestLast" + System.currentTimeMillis();
    }

    public static String randomPassword() {
        return "Test" + System.currentTimeMillis() + "!";
    }
}

