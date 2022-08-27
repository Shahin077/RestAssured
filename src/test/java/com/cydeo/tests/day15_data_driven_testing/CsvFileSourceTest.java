package com.cydeo.tests.day15_data_driven_testing;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
public class CsvFileSourceTest {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/zipCodes.csv", numLinesToSkip = 1)
    public void zipCodesTest(String state, String city, int zipCodeCount){

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("state", state);
        paramsMap.put("city", city);

        given().accept(ContentType.JSON)
                .and().pathParams(paramsMap)
                .when().get("/us/{state}/{city}")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("places", hasSize(zipCodeCount));

    }
}
