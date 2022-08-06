package com.cydeo.utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {

    @BeforeAll // @BeforeAll in junit 4
    public static void setup(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.api.url");
    }
}
