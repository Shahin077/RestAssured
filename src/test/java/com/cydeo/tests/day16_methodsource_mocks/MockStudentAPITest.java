package com.cydeo.tests.day16_methodsource_mocks;

import static io.restassured.RestAssured.*;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MockStudentAPITest {

    @BeforeAll
    public static void setUp(){
      baseURI= ConfigurationReader.getProperty("mock_server_url");
    }

    @Test
    public void testStudent(){

        given().accept(ContentType.JSON)
                .when().get("/students/1")
                .then().assertThat().statusCode(200)
                .log().all();
    }
}
