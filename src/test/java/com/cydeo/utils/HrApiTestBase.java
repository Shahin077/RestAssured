package com.cydeo.utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HrApiTestBase {

    @BeforeAll // @BeforeAll in junit 4
    public static void setup(){

        RestAssured.baseURI = ConfigurationReader.getProperty("hr.api.url");
    }
}

