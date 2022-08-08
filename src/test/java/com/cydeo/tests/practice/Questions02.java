package com.cydeo.tests.practice;

import com.cydeo.utils.HrApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Questions02  extends HrApiTestBase {

        /**
         * - Given accept type is Json
         * - Path param value- US
         * - When users sends request to /countries
         * - Then status code is 200
         * - And Content - Type is Json
         * - And country_id is US
         * - And Country_name is United States of America
         * - And Region_id is 2
         */
        @DisplayName("Question01")
        @Test
        public void testWithJsonPath() {
            Response response = given().accept(ContentType.JSON)
                    .and().pathParam("country_id", "US").when().get("/countries/{country_id}");

            JsonPath jsonPath = response.jsonPath();


            assertEquals(HttpStatus.SC_OK, response.statusCode());
            assertEquals(ContentType.JSON.toString(), response.contentType());
            assertEquals("US", jsonPath.get("country_id"));
            assertEquals("United States of America", jsonPath.get("country_name"));
            assertEquals(2, jsonPath.getInt("region_id"));

        }

    /**Q2:
     - Given accept type is Json
     - Query param value - q={"department_id": 80}
     - When users sends request to /employees
     - Then status code is 200
     - And Content - Type is Json
     - And all job_ids start with 'SA'
     - And all department_ids are 80
     - Count is 25
     *
     */

    @DisplayName("Question02")
    @Test
    public void testWithJsonPath2() {

        Response response = given().accept(ContentType.JSON).log().all()
                .and().queryParam("q", "{\"department_id\":80}").when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        List<String> allJobID = jsonPath.getList("items.job_id");

        List<Integer> allDepID = jsonPath.getList("items.department_id");


        for (String each : allJobID) {
            assertTrue(each.startsWith("SA"));
        }
        for (Integer each : allDepID) {
            assertEquals(80, each);
        }
        assertTrue(25 == allDepID.size() && 25 == allJobID.size());
    }

    /**
     * Q3:
     * - Given accept type is Json
     * -Query param value q= region_id 3
     * - When users sends request to /countries
     * - Then status code is 200
     * - And all regions_id is 3
     * - And count is 6
     * - And hasMore is false
     * - And Country_name are;
     * Australia,China,India,Japan,Malaysia,Singapore
     */
    @DisplayName("Question03")
    @Test
    public void testWithJsonPath3() {

        Response response = given().accept(ContentType.JSON).and()
                .queryParam("q", "{\"region_id\": 3}").when()
                .get("/countries");

        JsonPath jsonPath = response.jsonPath();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        List<Integer> allRegionID = jsonPath.getList("items.region_id");
        List<String> allCountryName = jsonPath.getList("items.country_name");

        for (Integer each : allRegionID) {
            assertEquals(3, each);
        }
        assertEquals(6, allRegionID.size());


        assertEquals(false,jsonPath.getBoolean("items.hasMore"));

        List<String> country = new ArrayList<>(Arrays.asList("Australia", "China", "India", "Japan", "Malaysia","Singapore"));

        assertTrue(allCountryName.containsAll(country));


    }
}

