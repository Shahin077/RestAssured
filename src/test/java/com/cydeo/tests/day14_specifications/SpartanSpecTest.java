package com.cydeo.tests.day14_specifications;

import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;

import io.restassured.module.jsv.JsonSchemaValidator;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import com.cydeo.utils.SpartanSecureTestBase;



public class SpartanSpecTest extends SpartanSecureTestBase {


    @Test
    public void allSpartansTest() {
                 given().spec(requestSpec)
                .when().get("/spartans")
                .then().spec(responseSpec).log().all();


    }

    @Test
    public void singleSpartansTest() {
                 given().spec(requestSpec)
                .and().pathParam("id", 15)
                .when().get("/spartans/{id}")
                .then().spec(responseSpec).log().all();

    }
}