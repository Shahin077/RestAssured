package com.cydeo.utils;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class SpartanRestUtils {
   private static String baseUrl = ConfigurationReader.getProperty("spartan.api.url");

    public static void deleteSpartanById(int spartanId){
        given().pathParam("id" , spartanId)
                .when().delete(baseUrl + "/{id}")
                .then().log().all();
    }


}
