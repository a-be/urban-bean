package com.hirath.urbanbean;

import com.mifmif.common.regex.Generex;

public class TestDataGenerator {
    private static final Generex STRING_GENERATOR = new Generex("[A-Za-z]{100}");

    public static String randomString(){
        return  STRING_GENERATOR.random();
    }
}
