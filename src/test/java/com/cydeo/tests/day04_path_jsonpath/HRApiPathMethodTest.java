package com.cydeo.tests.day04_path_jsonpath;

import com.cydeo.utils.HrApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class HRApiPathMethodTest extends HrApiTestBase {

    @Test
    public void readCountriesUsingPathTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("countries");

        assertEquals( 200, response.statusCode());

        System.out.println("first country id = "+response.path("items.country_id[0]"));
        System.out.println("first country name = "+response.path("items.country_name[0]"));

        List<String> countryName = response.path("items.country_name");
        for (String names : countryName) {
            System.out.println(names);
        }

    }
}
