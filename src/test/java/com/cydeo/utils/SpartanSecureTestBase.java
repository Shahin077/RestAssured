package com.cydeo.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public abstract class SpartanSecureTestBase {

    protected static RequestSpecification requestSpec ;

    protected static ResponseSpecification responseSpec;

    @BeforeAll // @BeforeAll in junit 4
    public static void setup(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartan.secure.api.url");

        requestSpec = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin");

        responseSpec = expect().statusCode(200).contentType(ContentType.JSON);
    }
}
