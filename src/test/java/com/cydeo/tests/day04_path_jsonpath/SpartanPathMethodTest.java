package com.cydeo.tests.day04_path_jsonpath;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanPathMethodTest extends SpartanTestBase {
    /**
     * Given accept is json
     * And path param id is 13
     * When I send get request to /api/spartans/{id}
     * ----------
     * Then status code is 200
     * And content type is json
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879"
     */
    @DisplayName("GET /spartan/{id} and path()")
    @Test
    public void readSpartanJsonUsingPathTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 13)
                .when().get("/spartans/{id}");

        /**
         {
         "id": 13,
         "name": "Jaimie",
         "gender": "Female",
         "phone": 7842554879
         }
         */
        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));
        System.out.println("gender = " + response.path("gender"));
        System.out.println("phone = " + response.path("phone"));

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        int spartanId = response.path("id");
        assertEquals(13, spartanId);
        assertEquals("Jaimie", response.path("name"));
        assertEquals("Female",  response.path("gender"));
        assertEquals(7842554879L, (long)response.path("phone"));
        //assertEquals(7842554879L, Long.valueOf(""+response.path("phone"))); tricky

    }

    /**
     Given accept is json
     When I send get request to /api/spartans
     Then status code is 200
     And content type is json
     And I can navigate json array object
     */

    @DisplayName("GET /spartans and path()")
    @Test
    public void readSpartanJsonArrayUsingPathTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans");

        assertEquals(HttpStatus.SC_OK,response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        System.out.println("first spartan id = " + response.path("id[0]"));
        System.out.println("first spartan name = " + response.path("name[0]"));

        //print last spartan id and name
        System.out.println("first spartan id = " + response.path("id[-1]"));
        System.out.println("first spartan name = " + response.path("name[-1]"));

        List <Integer> allIds = response.path("id");

        System.out.println("all ids size  " + allIds.size());
        System.out.println(allIds);

        assertTrue(allIds.contains(22) && allIds.size()==100);


        List <String> names = response.path("name");

        names.forEach(name -> System.out.println("Hi " + name));

        for (String name : names) {
            System.out.println("Bye "+name);

        }


        /**
         *  given().accept(ContentType.JSON)
         *             .when().get().then().log().body()
         *             .assertThat().statusCode(HttpStatus.SC_OK)
         *             .and().assertThat().contentType(ContentType.JSON)
         *             .assertThat().body("id[0]",equalTo(1))
         *             .assertThat().body("name[0]",equalTo("Meade"))
         *             .assertThat().body("gender[0]",equalTo("Male"))
         *            .assertThat().body("phone[0]",equalTo(3584128232L))
         *             .assertThat().body("id[1]",equalTo(2))
         *             .assertThat().body("name[1]",equalTo("Nels"))
         *           .assertThat().body("gender[1]",equalTo("Male"))
         *      .assertThat().body("phone[1]",equalTo(4218971348L));}}
         */
    }



}



