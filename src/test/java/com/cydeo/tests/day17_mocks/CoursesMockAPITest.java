package com.cydeo.tests.day17_mocks;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class CoursesMockAPITest {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("mock_server_url");
    }

    @Test
    public void coursesMockTest() {

        given().accept(ContentType.JSON)
                .when().get("/courses")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("courseId", hasItems(1,2,3),
                        "courseName", hasItems("Java SDET", "Java Developer", "Cyber Security Analyst"))
                .log().all();
    }
}