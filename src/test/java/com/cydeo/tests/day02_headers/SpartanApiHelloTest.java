package com.cydeo.tests.day02_headers;

/**
 add new package day02_headers
 Add new class SpartanApiHelloTest

 When I send GET request to http://3.93.242.50:8000/api/hello
 Then status code should be 200
 And response body should be equal to "Hello from Sparta"
 */

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanApiHelloTest {

    String url = "http://44.203.59.210:8000/api/hello";

    @DisplayName("Get api/hello")
    @Test
    public  void helloApiTest(){
        Response response = when().get(url);

        assertEquals(200 , response.statusCode());

        response.prettyPrint();

        assertEquals("Hello from Sparta",response.body().asString());

        assertEquals("text/plain;charset=UTF-8",response.contentType());
    }

    @DisplayName("Get api/hello -bdd")
    @Test
    public  void helloApiBddTest(){

        when().get(url)
                .then().assertThat().statusCode(200)
                .and().contentType("text/plain;charset=UTF-8");
    }


}
