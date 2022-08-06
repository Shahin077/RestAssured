package com.cydeo.tests.practice;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Q1_practice {

    /**
     * - Given accept type is Json
     * - When user sends request to https://jsonplaceholder.typicode.com/posts
     * <p>
     * -Then print response body
     * <p>
     * - And status code is 200
     * - And Content - Type is Json
     */
    String url = "https://jsonplaceholder.typicode.com/posts";

    @Test
    public void statusCodeAssert() {
        Response response = given().log().all().accept(ContentType.JSON)
                .when().get(url);

        // response.prettyPrint();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertTrue(response.contentType().contains(ContentType.JSON.toString()));
    }

    /**
     * Q2:
     * - Given accept type is Json
     * - Path param "id" value is 1
     * - When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}
     * - Then status code is 200
     * <p>
     * -And json body contains "repellat provident"
     * - And header Content - Type is Json
     * - And header "X-Powered-By" value is "Express"
     * - And header "X-Ratelimit-Limit" value is 1000
     * - And header "Age" value is more than 100
     * <p>
     * - And header "NEL" value contains "success_fraction"
     */

    @Test
    public void statusCodeAssertBalaca() {
        int id = 1;
        Response response = given().log().all().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().get(url + "/{id}");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertTrue(response.asString().contains("repellat provident"));

        assertTrue(response.contentType().contains(ContentType.JSON.toString()));
        assertEquals("Express", response.getHeader("X-Powered-By"));
        assertEquals(1000, Integer.parseInt(response.getHeader("X-Ratelimit-Limit")));


        assertTrue(Integer.parseInt(response.getHeader("Age")) > 100);
        assertTrue(response.getHeader("NEL").contains("success_fraction"));

    }

    /**
     * - Given accept type is Json
     * - Path param "id" value is 12345
     * - When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}
     * - Then status code is 404
     * <p>
     * - And json body contains "{}"
     */
    @Test
    public void statusCodeAssertBalacaBoyuk() {
        int id = 12345;
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().get(url + "/{id}");

        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
        assertTrue(response.asString().contains("{}"));

    }

    /**
     * Given accept type is Json
     * - Path param "id" value is 2
     * - When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}/comments
     * - Then status code is 200
     * <p>
     * - And header Content - Type is Json
     * - And json body contains "Presley.Mueller@myrl.com",  "Dallas@ole.me" , "Mallory_Kunze@marie.org"
     */
    @Test
    public void statusCodeAssertBalacaBoyukKucuk() {

        int id = 2;


        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id", id)
                .when().get(url + "/{id}/comments");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertTrue(response.getHeader("Content-Type").contains(ContentType.JSON.toString()));
        assertTrue(response.getBody().asString().contains("Presley.Mueller@myrl.com"));
        assertTrue(response.getBody().asString().contains("Dallas@ole.me"));
        assertTrue(response.getBody().asString().contains("Mallory_Kunze@marie.org"));

    }

    /**
     * - Given accept type is Json
     * - Query Param "postId" value is 1
     * - When user sends request to  https://jsonplaceholder.typicode.com/comments
     * - Then status code is 200
     * <p>
     * - And header Content - Type is Json
     * <p>
     * - And header "Connection" value is "keep-alive"
     * - And json body contains "Lew@alysha.tv"
     */
    String url1 = "https://jsonplaceholder.typicode.com/comments";

    @Test
    public void statusCodeAssertBalacaBoyukKucukOrta() {
        String key = "postId";
        int value = 1;

        Response response = given().accept(ContentType.JSON)
                .and().queryParam(key, value)
                .when().get(url1);

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertTrue(response.getHeader("Content-Type").contains(ContentType.JSON.toString()));
        assertTrue(response.getHeader("Connection").contains("keep-alive"));
        assertTrue(response.getBody().asString().contains("Lew@alysha.tv"));


    }

    /**
     * - Given accept type is Json
     * - Query Param "postId" value is 333
     * - When user sends request to  https://jsonplaceholder.typicode.com/comments
     * <p>
     * - And header Content - Type is Json
     * <p>
     * - And header "Content-Length" value is 2
     * - And json body contains "[]"
     */
    @Test
    public void statusCodeAssertBalacaBoyukKucukOrtaAz() {
        String key = "postId";
        int value = 333;



        Response response = given().accept(ContentType.JSON)
                .and().queryParam(key ,value)
                .when().get(url1);

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertTrue(response.getHeader("Content-Type").contains(ContentType.JSON.toString()));
        assertEquals(2, Integer.parseInt(response.getHeader("Content-Length")));
        assertTrue(response.getBody().asString().contains("[]"));
    }
}