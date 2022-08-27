package com.cydeo.tests.day15_data_driven_testing;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CvsSourceDDTTest {

    @ParameterizedTest
    @CsvSource({"7, 5, 12",
            "3, 99 , 102",
            "32, 44, 76",
            "38, 41, 79"})
    public void basicAddTest(int num1, int num2, int expSum) {
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("expSum = " + expSum);
        int actSum = num1 + num2;
        System.out.println("actSum = " + actSum);
        assertThat(actSum, equalTo(expSum));
    }

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }

    @ParameterizedTest
    @CsvSource({"New York City, NY",
            "Denver, CO",
            "Boston, MA",
            "East Pittsburgh, PA",
            "Raleigh, NC",
            "San Diego, CA",
            "Baltimore, MD",
            "Fairfax, VA"})
    public void cityAndStateZipCodeAPITest(String city, String state) {

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("state", state);
        paramsMap.put("city", city);

        given().accept(ContentType.JSON)
                .and().pathParams(paramsMap)
                .when().get("/us/{state}/{city}")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("'place name'", equalTo(city))
                .log().all();
    }

}
