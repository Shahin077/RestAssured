package com.cydeo.tests.day09_post_put_delete;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.assertThat;;
import static org.hamcrest.Matchers.*;


import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanPostTest extends SpartanTestBase {

    /**
     given accept is json and content type is json
     and body is:
     {
     "gender": "Male",
     "name": "TestPost1"
     "phone": 1234567425
     }
     When I send POST request to /spartans
     Then status code is 201
     And content type is json
     And "success" is "A Spartan is Born!"
     Data name, gender , phone matches my request details
     */

    @DisplayName("POST new spartan using string json")
    @Test
    public void addNewSpartanAsJsonTest() {
        String jsonBody = "{\n" +
                "     \"gender\": \"Male\",\n" +
                "     \"name\": \"TestPost1\"\n" +
                "     \"phone\": 1234567425\n" +
                "     }";

    }
}
