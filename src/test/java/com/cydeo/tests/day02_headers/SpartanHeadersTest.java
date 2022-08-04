package com.cydeo.tests.day02_headers;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanHeadersTest {
    /**
     • When I send a GET request to
     • spartan_base_url/api/spartans
     • Then Response STATUS CODE must be 200
     • And I Should see all Spartans data in JSON format
     */

    String url = "http://44.203.59.210:8000/api/spartans";

    @DisplayName("GET /api/spartans and expect Json response")
    @Test
    public void getAllSpartansHeaderTest(){
         when().get(url)
                 .then().assertThat().statusCode(200)
                 .and().contentType(ContentType.JSON);
    }

    /**
     * Given Accept type is application/xml
     • When I send a GET request to
     --------------------------------
     • spartan_base_url/api/spartans
     • Then Response STATUS CODE must be 200
     • And I Should see all Spartans data in xml format
     */

    @DisplayName("GET /api/spartans and expect Xml format")
    @Test
    public void getAllSpartansHeaderBodyXmlTest(){
        given().accept(ContentType.XML)
                .when().get(url)
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.XML);
    }

    @Test
    public void readres(){
        Response response = given().accept(ContentType.JSON)
                .when().get(url);

        System.out.println("Date header = " + response.getHeader("Date"));

        Headers headers = response.getHeaders();
        System.out.println(headers);

        assertTrue(response.getHeader("Keep-Alive") !=null);
    }
}
