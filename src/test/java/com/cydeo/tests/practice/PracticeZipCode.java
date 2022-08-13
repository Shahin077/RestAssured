package com.cydeo.tests.practice;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class PracticeZipCode {

    /**
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contains following information
     *     post code is 22031
     *     country  is United States
     *     country abbreviation is US
     *     place name is Fairfax
     *     state is Virginia
     *     latitude is 38.8604
     */
    @Test
    public void lokbatan(){
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 22031)
                .when().get("/us/{zipcode}");

        response.prettyPrint();
    }
}
