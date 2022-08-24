package com.cydeo.utils;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public abstract class BookItAPITestBase {

    protected static RequestSpecification teacherReqSpec;

    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("bookIt.base.url");

        String accessToken = getAccessToken(ConfigurationReader.getProperty("teacher_email"), ConfigurationReader.getProperty("teacher_password"));

        teacherReqSpec = given().accept(ContentType.JSON).
                and().header("Authorization", accessToken)
                .log().all();


    }

    public static String getAccessToken(String email, String password){

        String accessToken = given().accept(ContentType.JSON)
                .and().queryParam("email", email)
                .and().queryParam("password", password)
                .when().get("/sign")
                .then().assertThat().statusCode(200)
                .and().extract().path("accessToken");

        assertThat(accessToken , not(emptyOrNullString()));

        return accessToken;
    }

}
