package com.cydeo.tests.day15_data_driven_testing;

import com.cydeo.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class JUnitValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {22, 56, 8, 45, 74, 123, 654 })
    public void number(int num){
        System.out.println("num = " + num);
        assertThat(num, is(greaterThan(0)));

    }
    @ParameterizedTest
    @ValueSource(strings = {"Vugar", "Shina", "Dzerassa", "Eda", "Kevin","Shahin","Nadir"} )
    public void testNames(String name) {
        System.out.println("Hi! "+ name);
        assertThat(name, not(blankOrNullString()));
    }

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }

    @ParameterizedTest
    @ValueSource(ints = {22102, 22031, 22034, 11209, 15090, 15237,12345,20879,21224,33433})
    public void zipCodeTest(int zipCode) {
        //each time new zipcode value from ValueSource is assigned to zipCode variable
        given().accept(ContentType.JSON)
                .and().pathParam("postal-code", zipCode) //using zipCode as path parameter
                .when().get("/us/{postal-code}")
                .then().assertThat().statusCode(200)
                .log().all();


    }
}
