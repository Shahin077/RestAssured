package com.cydeo.tests.day13_access_token_specs;

import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class BookItAccessTokenTest {

    /**
     * add new package day13_access_token_specs
     *
     * Add new class BookItAccessTokenTest
     * Given accept type is Json
     * And Query params: email = blyst6@si.edu & password = barbabaslyst
     * When I send GET request to
     * Url: https://cybertek-reservation-api-qa3.herokuapp.com/sign
     * Then status code is 200
     * And accessCode should be present
     */

    @Test
    public void bookItLoginTest(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("email" ,"blyst6@si.edu")
                .and().queryParam("password", "barbabaslyst")
                .when().get("https://cybertek-reservation-api-qa3.herokuapp.com/sign");

        response.prettyPrint();

        System.out.println("status Code = " + response.statusCode());
        assertThat(HttpStatus.SC_OK, equalTo(response.statusCode()));

        String accessToken = response.path("accessToken");
        System.out.println("accessToken = " + accessToken);
        Assertions.assertTrue(accessToken != null && !accessToken.isEmpty());
    }
}
